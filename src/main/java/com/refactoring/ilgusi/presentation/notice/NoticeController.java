package com.refactoring.ilgusi.presentation.notice;

import com.refactoring.ilgusi.domain.notice.NoticeService;
import com.refactoring.ilgusi.domain.notice.dto.NoticeResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@RequiredArgsConstructor
@Controller
public class NoticeController {
    private final NoticeService noticeService;

    //공지사항 목록으로 이동
    @GetMapping("/noticeListPage")
    public String noticeListPage () {
        return "notice/noticeList";
    }

    //공지사항 목록으로 이동
    @GetMapping("/noticeList")
    @ResponseBody
    public ResponseEntity<NoticeResponseDto> noticeListApi (int reqPage, String keyword) {
        System.out.println(noticeService.selectNoticeListApi(reqPage, keyword).toString());
        return ResponseEntity.ok(noticeService.selectNoticeListApi(reqPage, keyword));
    }

    //공지사항 작성
    @RequestMapping("/noticeWriteFrm.do")
    public String noticeWriteFrm () {
        return "notice/noticeWriteFrm";
    }

   /* //공지사항 등록
    @RequestMapping("/insertNotice.do")
    public String insertNotice(MultipartHttpServletRequest mRequest, Model model, HttpServletRequest request) {
        String root = request.getSession().getServletContext().getRealPath("/");
        String path = root+"upload/notice/";
        System.out.println("파일 경로 : " + path);
        String filename = "";
        String filepath = "";

        MultipartFile file = mRequest.getFile("filename");

        if(!file.isEmpty()) {
            System.out.println("파일이 있음");
            filename = file.getOriginalFilename();
            filepath = new FileNameOverlap().rename(path, filename);

            byte[] bytes;
            try {
                bytes = file.getBytes();
                File upFile = new File(path+filepath);
                FileOutputStream fos = new FileOutputStream(upFile);
                BufferedOutputStream bos = new BufferedOutputStream(fos);
                bos.write(bytes);
                bos.close();

            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }else {
            System.out.println("파일이 없음 ");
            filename = null;
            filepath = null;
        }


        Notice n= new Notice();
        n.setFilename(filename);
        n.setFilepath(filepath);
        n.setNTitle(request.getParameter("nTitle"));
        n.setNContent(request.getParameter("nContent"));

        int result = service.insertNotice(n);
        if(result > 0 ) {
            model.addAttribute("msg","등록되었습니다.");
        }else {
            model.addAttribute("msg","등록실패.");
        }
        model.addAttribute("loc", "/noticeList.do?reqPage=1&keyword=");
        return "common/msg";
    }

    //공지사항 내용 보기
    @RequestMapping("/noticeView.do")
    public String noticeView (int nNo, Model model, Notice n) {
        n.setNNo(nNo);
        n = service.selectNoticeView(nNo);
        model.addAttribute("n", n);
        if(n == null) {
            System.out.println("NoticeView :  n = null");
        }
        return "notice/noticeView";
    }

    //공지사항 삭제
    @RequestMapping("/deleteNotice.do")
    public String deleteNotice(int nNo, Model model) {
        int result = service.deleteNotice(nNo);
        if(result > 0 ) {
            model.addAttribute("msg", "게시글을 삭제합니다.");
        }else {
            model.addAttribute("msg", "실패");
        }
        model.addAttribute("loc", "noticeList.do");
        return "common/msg";
    }

    //공지사항 수정 페이지로 이동
    @RequestMapping("/updateNoticeFrm.do")
    public String updateNoticeFrm (int nNo, Model model, Notice n) {
        n.setNNo(nNo);
        n = service.selectNoticeView(nNo);
        model.addAttribute("n", n);

        if(n == null) {
            System.out.println("UpdateNotice :  n = null");
        }
        return "notice/noticeUpdateFrm";
    }


    // 공지사항 수정
    @RequestMapping("/updateNotice.do")
    public String updateNotice(int nNo, MultipartHttpServletRequest mRequest, Model model,HttpServletRequest request) {

        String root = request.getSession().getServletContext().getRealPath("/");
        String path = root+"upload/notice/";
        System.out.println("파일 경로 : " + path);
        String filename = "";
        String filepath = "";
        MultipartFile file = mRequest.getFile("filename");

        if(!file.isEmpty()) {
            System.out.println("파일이 있음");
            filename = file.getOriginalFilename();
            filepath = new FileNameOverlap().rename(path, filename);
            byte[] bytes;
            try {
                bytes = file.getBytes();
                File upFile = new File(path+filepath);
                FileOutputStream fos = new FileOutputStream(upFile);
                BufferedOutputStream bos = new BufferedOutputStream(fos);
                bos.write(bytes);
                bos.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }else {
            System.out.println("파일이 없음 ");
            filename = null;
            filepath = null;
        }

        Notice n= new Notice();
        n.setNNo(nNo);
        n.setFilename(filename);
        n.setFilepath(filepath);
        n.setNTitle(request.getParameter("nTitle"));
        n.setNContent(request.getParameter("nContent"));
        System.out.println("filename:" + filename);

        int result = service.updateNotice(n);
        System.out.println(result);
        if(result > 0 ) {
            model.addAttribute("msg","수정되었습니다.");
        }else {
            model.addAttribute("msg","수정실패.");
        }
        model.addAttribute("loc", "/noticeList.do?reqPage=1&keyword=");

        return "common/msg";
    }

    //관리자 공지사항리스트
    @RequestMapping("/adminNoticeList.do")
    public String adminNoticeList ( Model model, int reqPage, String keyword) {
        *//*NoticePageData npd = service.selectNoticeList(reqPage,keyword);
        model.addAttribute("list", npd.getList());
        model.addAttribute("pageNavi", npd.getPageNavi());*//*

        return "admin/noticeList";
    }
*/

}

