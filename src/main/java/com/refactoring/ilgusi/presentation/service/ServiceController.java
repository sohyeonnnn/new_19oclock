package com.refactoring.ilgusi.presentation.service;

import com.refactoring.ilgusi.common.CommonUtil;
import com.refactoring.ilgusi.common.MsgRedirectHelper;
import com.refactoring.ilgusi.common.ResultData;
import com.refactoring.ilgusi.domain.category.dto.MainCategoryDto;
import com.refactoring.ilgusi.domain.category.interfaces.CategoryService;
import com.refactoring.ilgusi.domain.favorite.interfaces.FavoriteService;
import com.refactoring.ilgusi.domain.member.Member;
import com.refactoring.ilgusi.domain.service.ServiceFile;
import com.refactoring.ilgusi.domain.service.dto.ServiceInfoDto;
import com.refactoring.ilgusi.domain.service.dto.ServiceInsertDto;
import com.refactoring.ilgusi.domain.service.dto.ServicePageDto;
import com.refactoring.ilgusi.domain.service.interfaces.ServiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class ServiceController {
    private final ServiceService serviceService;
    private final FavoriteService favoriteService;
    private final CategoryService categoryService;

    @ResponseBody
    @PostMapping("/isPossibleMakeService")
    public ResponseEntity<?> isPossibleMakeService(@RequestParam("memberNo") int memberNo) {
        return ResponseEntity.ok(ResultData.builder().data(serviceService.selectFreelancerServiceCount(memberNo)).build());
    }

    @GetMapping("/serviceJoinFrm")
    public String serviceJoinFrm() {
        return "freelancer/servicejoin";
    }

    @PostMapping("/insertService")
    public String serviceJoin(@ModelAttribute ServiceInsertDto dto,
                              @RequestParam("ssImg") MultipartFile[] files,
                              HttpServletRequest request,
                              Model model) {

        String msg = null;
        String loc = "/";
                String uploadDir = request.getServletContext().getRealPath("/upload/service/");

        try {
            List<ServiceFile> fileList = handleFileUpload(files, uploadDir);

            if (fileList.isEmpty()) {
                throw new IllegalStateException("업로드된 파일이 없습니다.");
            }

            dto.setServiceImg(fileList.get(0).getFilepath());
            serviceService.insertService(dto, fileList);

            msg =  "서비스를 등록하였습니다.";
            loc =  "redirect:/freelancerServiceList?order=rejected";
        } catch (Exception e) {
            msg = "서비스 등록 중 오류가 발생했습니다: " + e.getMessage();
        }

        return MsgRedirectHelper.success(model,msg,loc);
    }

    private List<ServiceFile> handleFileUpload(MultipartFile[] files, String uploadDir) throws IOException {
        List<ServiceFile> fileList = new ArrayList<>();

        File dir = new File(uploadDir);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        for (MultipartFile file : files) {
            if (file.isEmpty()) continue;

            String originalFilename = file.getOriginalFilename();
            String renamedFilename = CommonUtil.fileRename(uploadDir, originalFilename);

            File dest = new File(uploadDir, renamedFilename);
            file.transferTo(dest);

            ServiceFile serviceFile = new ServiceFile();
            serviceFile.setFilename(originalFilename);
            serviceFile.setFilepath(renamedFilename);
            fileList.add(serviceFile);
        }

        return fileList;
    }

    @GetMapping("/freelancerServiceList")
    public String freelancerServiceList(@ModelAttribute("loginMember") Member m, Model model, @RequestParam("order") String order) {
        List<ServiceInfoDto> list = serviceService.selectOrderedServiceList(m.getMemberNo(), order);
        model.addAttribute("list", list);
        model.addAttribute("order", order);
        return "freelancer/freelancerServiceList";
    }

    // 프리랜서 마이페이지 - 서비스 삭제하기
    @RequestMapping("/delService")
    public String deleteService(int serviceNo, Model model) {
        serviceService.deleteService(serviceNo);

        String msg =  "서비스가 삭제되었습니다.";
        String loc =  "/freelancerServiceList?order=approved";

        return MsgRedirectHelper.success(model,msg,loc);
    }

    // serviceView 페이지 이동
    @RequestMapping("/serviceView")
    public String serviceView(@SessionAttribute(value = "loginMember", required = false) Member member, int serviceNo, Model model/*, int reqPage*/) {
        ServiceInfoDto serviceInfo = serviceService.selectServiceView(serviceNo);
        model.addAttribute("service", serviceInfo);

        List<ServiceFile> fileList = serviceService.selectServiceFileList(serviceNo);
        List<Map<String, Object>> fileListForView = new ArrayList<>();
        for (int i = 0; i < fileList.size(); i++) {
            ServiceFile file = fileList.get(i);
            Map<String, Object> fileMap = new HashMap<>();
            fileMap.put("index", i);
            fileMap.put("isFirst", i == 0);
            fileMap.put("indexPlusOne", i + 1);
            fileMap.put("filepath", file.getFilepath());
            fileListForView.add(fileMap);
        }
        model.addAttribute("fileList", fileListForView);

        boolean isFavoriteChecked = false;
        if(member == null){
            isFavoriteChecked = favoriteService.isFavoriteChecked(member.getMemberNo(), serviceNo);
        }
        model.addAttribute("favoriteCheck",isFavoriteChecked);


        // 해당 유저가 등록한 다른서비스 불러오기
        List<ServiceInfoDto> serviceList = serviceService.selectOrderedServiceList(serviceInfo.getFreeNo(), "approved");
        model.addAttribute("serviceList", serviceList);

        /*
        // 리뷰 리스트 불러오기 + 페이징
        ReviewPageData rpd = service.selectReviewList(sNo, reqPage,mNo);
        if (rpd.getList().size() == 0) {
            System.out.println("리뷰 없음");
        } else {
            System.out.println("리뷰있음");
            System.out.println(rpd.getPageNavi());
        }

        model.addAttribute("reviewList", rpd.getList());
        model.addAttribute("pageNavi", rpd.getPageNavi());

*/
        return "/service/serviceView";
    }

    @GetMapping("/serviceListPage")
    public String serviceListPage(int mainCategoryCd, int categoryCd, int reqPage, String order, String keyword, Model model) {
        List<MainCategoryDto> categoryList = categoryService.selectMainCategoryList(mainCategoryCd);
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("order", order);
        model.addAttribute("keyword", keyword);
        model.addAttribute("mainCategoryCd", mainCategoryCd);
        model.addAttribute("categoryCd", categoryCd);
        return "/service/serviceList";
    }

    @GetMapping("/serviceList")
    public ResponseEntity<ServicePageDto> serviceList(int mainCategoryCd, int categoryCd, int reqPage, String order, String keyword) {
        ServicePageDto servicePageDto = serviceService.selectServiceListApi(mainCategoryCd, categoryCd, reqPage, order, keyword);
        return ResponseEntity.ok(servicePageDto);
    }



    /*// (영재) 리뷰갯수 구하기
    @RequestMapping("/reviewListSize.do")
    public void reviewListSize(String mId, Model model) {
        List<ServiceReview> list = service.reviewListSize(mId);
        System.out.println("mid>>>>>" + mId);
        model.addAttribute("list", list);
        System.out.println("list>>>>>>평점" + list);
    }*/

    /*

    // (문정)사용자 마이페이지 - 거래후기 쓰기
    @RequestMapping("/serviceReviewWrite.do")
    public String serviceReviewWrite(int tNo, int sNo, String mId, String sImg, String sTitle, Model model) {
        model.addAttribute("tNo", tNo);
        model.addAttribute("sNo", sNo);
        model.addAttribute("mId", mId);
        model.addAttribute("sImg", sImg);
        model.addAttribute("sTitle", sTitle);
        return "service/serviceReviewWrite";
    }

    // (문정) 마이페이지 - 서비스 후기 등록
    @RequestMapping("/serviceReviewInsert.do")
    public String serviceReviewInsert(ServiceReview data, Model model) {
        int result = service.serviceReviewInsert(data);
        if (result > 0) {
            result = service.serviceReviewSuccess(data.getTNo());
            if (result > 0) {
                model.addAttribute("msg", "리뷰를 등록하였습니다.");
                result = service.serviceUpdateSRate(data.getSNo());
                if (result > 0)
                    System.out.println("[등록]서비스테이블에 s_rate 수정 성공");
            }
        }
        return "/service/reviewDone";
    }

    // (문정) 마이페이지 - 거래 후기 작성한거 확인
    @RequestMapping("/serviceReviewView.do")
    public String serviceReviewView(ServiceReview data, Model model) {
        ServiceReview sr = service.serviceReviewView(data);
        model.addAttribute("review", sr);
        return "/service/serviceReviewUpdate";
    }

    // (문정) 서비스 리뷰 수정
    @RequestMapping("/serviceReviewUpdate.do")
    public String serviceReviewUpdate(ServiceReview review, Model model) {
        int result = service.serviceReviewUpdate(review);
        if (result > 0) {
            model.addAttribute("msg", "리뷰를 수정하였습니다.");
            result = service.serviceUpdateSRate(review.getSNo());
            if (result > 0)
                System.out.println("[수정]서비스테이블에 s_rate 수정 성공");
        }
        return "/service/reviewDone";
    }

    // (문정) 서비스 리뷰 삭제
    @RequestMapping("/serviewReviewDelete.do")
    public String serviewReviewDelete(int rNo, int tNo, int sNo, Model model) {
        int result = service.serviceReviewDelete(rNo);
        if (result > 0) {
            result = service.serviceTradeStatusUpdate(tNo);
            if (result > 0) {
                model.addAttribute("msg", "리뷰를 삭제했습니다.");
                result = service.serviceUpdateSRate(sNo);
                if (result > 0)
                    System.out.println("[삭제]서비스테이블에 s_rate 수정 성공");
            }
        }
        return "/service/reviewDone";
    }*/

    // (다솜)serviceList
   /* @RequestMapping("/serviceList.do")
    public String serviceList(int cNo, int reqPage, String order, Model model, String keyword) {

        int numPerPage = 12;
        int end = reqPage * numPerPage;
        int start = end - numPerPage + 1;

        Service s = new Service();
        System.out.println("cNo : " + cNo);
        int maincateNum = 0;
        int subNo = 0;

        HashMap<String, Object> map = new HashMap<String, Object>();

        if (cNo % 10 == 0) {
            maincateNum = cNo;
            s.setSubCategory(subNo);
            map.put("sub", 0);

        } else {
            maincateNum = (cNo / 10) * 10;
            subNo = cNo;
            map.put("sub", subNo);
        }

        map.put("main", maincateNum);
        map.put("start", start);
        map.put("end", end);
        map.put("reqPage", reqPage);
        map.put("cNo", cNo);
        map.put("keyword", keyword);
        map.put("order", order);

        *//* map.put("order", order); *//*

        s.setMainCategory(maincateNum);
        s.setSubCategory(subNo);
        System.out.println("메인카테고리 : " + maincateNum);
        System.out.println("서브카테고리 : " + subNo);

        // 카테고리 리스트 불러오기
        ArrayList<Category> catList = service.categoryList(maincateNum);
        System.out.println("카테고리 리스트 사이즈 : " + catList.size());
        System.out.println("catList(1)값 : " + catList.get(1));

        // 서비스 리스트 불러오기

        ServicePageData spd = new ServicePageData();
        spd.setEnd(end);
        spd.setKeyword(keyword);
        spd.setReqPage(reqPage);
        spd.setStart(start);
        spd.setCNo(cNo);

        // 서비스 리스트 불러오기+페이징
        spd = service.servicePageList(map, reqPage, cNo, order);
        ArrayList<Service> serList = spd.getList();

        // 가격 => 천단위 콤마 찍기
        DecimalFormat formatter = new DecimalFormat("###,###");
        for (int i = 0; i < serList.size(); i++) {
            serList.get(i).setSPriceTxt(formatter.format(serList.get(i).getSPrice()));
        }

        // 맵 확인용 ArrayList
        ArrayList<HashMap<String, Object>> mapList = new ArrayList<HashMap<String, Object>>();
        mapList.add(map);

        if (mapList.size() != 0) {
            System.out.println("mapList(0) : " + mapList.get(0));
        }

        if (cNo % 10 == 0) {
            for (int i = 0; i < serList.size(); i++) {
                serList.get(i).setSubCategory(0);
            }
        }

        if (serList.size() > 0) {
            System.out.println("serList 사이즈 : " + serList.size());
            System.out.println("serList.get(0) : " + serList.get(0));
            System.out.println("serList.get(0).평점 : " + serList.get(0).getSRate());
            model.addAttribute("serviceList", spd.getList());

        } else if (serList.size() == 0) {
            System.out.println("serList 사이즈 : " + serList.size());
            model.addAttribute("noServiceList", "noServiceList");
        }

        if (cNo % 10 == 0) {
            model.addAttribute("c_no", serList.get(0).getMainCategory());
        } else {
            model.addAttribute("c_no", serList.get(0).getSubCategory());
        }

        switch (maincateNum) {
            case 10:
                model.addAttribute("mainCate", "디자인");
                break;
            case 20:
                model.addAttribute("mainCate", "ITㆍ프로그래밍");
                break;
            case 30:
                model.addAttribute("mainCate", "영상ㆍ사진ㆍ음향");
                break;
            case 40:
                model.addAttribute("mainCate", "교육");
                break;
            case 50:
                model.addAttribute("mainCate", "문서ㆍ글쓰기");
                break;
            case 60:
                model.addAttribute("mainCate", "비즈니스컨설팅");
                break;
            case 70:
                model.addAttribute("mainCate", "주문제작");
                break;
        }

        model.addAttribute("catList", catList);
        model.addAttribute("pageNavi", spd.getPageNavi());
        model.addAttribute("order", order);

        return "/service/serviceList";
    }*/
/*


    // (다솜) serviceView 페이지 이동 (로그인 안됐을때)
    @RequestMapping("/serviceView2.do")
    public String serviceView(int sNo, Model model, int reqPage) {
        System.out.println("서비스 컨트롤러-serviceView");
        System.out.println("서비스 상세보기 sNo: " + sNo);

        // 해당 서비스 정보 불러오기
        Service s = service.selectServiceView(sNo);

        DecimalFormat formatter = new DecimalFormat("###,###");
        s.setSPriceTxt(formatter.format(s.getSPrice()));

        System.out.println("메인카테고리 이름 : " + s.getMainCategoryName());
        System.out.println("서브카테고리 이름 : " + s.getSubCategoryName());

        if (s != null) {
            model.addAttribute("s", s);
        }
        int serviceNo = s.getSNo();

        // 브랜드 정보 불러오기
        String memberId = s.getMId();

        Member m = service.selectMemberName(memberId);
        model.addAttribute("m", m);

        // 서비스 파일 불러오기
        ArrayList<ServiceFile> fileList = service.fileList(sNo);
        System.out.println("fileList 사이즈 :" + fileList.size());
        System.out.println("fileList값:" + fileList.get(0));
        model.addAttribute("fileList", fileList);

        // 해당 유저가 등록한 다른서비스 불러오기
        ArrayList<Service> sList = service.userService(memberId);
        model.addAttribute("sList", sList);

        // 리뷰 리스트 불러오기 + 페이징
        ReviewPageData rpd = service.selectReviewList(sNo, reqPage,-1);
        if (rpd.getList().size() == 0) {
            System.out.println("리뷰 없음");
        } else {
            System.out.println("리뷰있음");
            System.out.println(rpd.getPageNavi());
        }

        model.addAttribute("reviewList", rpd.getList());
        model.addAttribute("pageNavi", rpd.getPageNavi());

        // 찜한내역 확인하기
        model.addAttribute("favoriteCheck", false);

        return "/service/serviceView";
    }

    // (문정) 결제 진행
    @RequestMapping("/insertServicePay.do")
    public String insertServicePay(ServicePay pay) {
        int result = service.insertServicePay(pay);
        if (result > 0) {
            result = service.updateTradeStatus(pay.getTNo());
        }
        return "redirect:/userTradeHistory.do?mNo=" + pay.getPNo();
    }

    // (도현)serviceList
    @RequestMapping("/serviceSearch.do")
    public String serviceSearch(@RequestParam(value = "page", defaultValue = "1") int page, String order, Model model,
                                String keyword) {
        if (!(keyword == null || keyword.equals(""))) {
            // 네비 기능
            int listPerPage = 20;
            int maxListCount;
            maxListCount = service.selectServiceCount(keyword);

            List<Service> list = service.searchService(maxListCount - ((page) * listPerPage) + 1,
                    maxListCount - ((page - 1) * listPerPage), keyword);
            int maxPrintPageCount = 5;
            int maxPageCount = service.selectMaxPageCount(listPerPage, maxListCount);
            int begin = maxPrintPageCount * (page / (maxPrintPageCount + 1)) + 1; // 네비 시작
            int end = (begin + 4) < maxPageCount ? begin + 4 : maxPageCount; // 네비 끝

            // 천단위 컴마 찍기
            DecimalFormat formatter = new DecimalFormat("###,###");
            for (int i = 0; i < list.size(); i++) {
                list.get(i).setSPriceTxt(formatter.format(list.get(i).getSPrice()));
            }
            model.addAttribute("list", list);
            model.addAttribute("begin", begin);
            model.addAttribute("end", end);
            model.addAttribute("maxListCount", formatter.format(maxListCount));
            model.addAttribute("maxPageCount", maxPageCount);
        }
        return "/service/serviceAllList";
    }
*/

}
