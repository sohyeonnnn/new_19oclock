package com.refactoring.ilgusi.presentation.notice;

import com.refactoring.ilgusi.common.CommonEnum;
import com.refactoring.ilgusi.common.CommonUtil;
import com.refactoring.ilgusi.common.MsgRedirectHelper;
import com.refactoring.ilgusi.domain.notice.NoticeService;
import com.refactoring.ilgusi.domain.notice.dto.NoticeInsertDto;
import com.refactoring.ilgusi.domain.notice.dto.NoticePageResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


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
    public ResponseEntity<NoticePageResponseDto> noticeListApi (int reqPage, String keyword) {
        System.out.println(noticeService.selectNoticeListApi(reqPage, keyword).toString());
        return ResponseEntity.ok(noticeService.selectNoticeListApi(reqPage, keyword));
    }

    //공지사항 작성
    @RequestMapping("/noticeWriteFrm")
    public String noticeWriteFrm () {
        return "notice/noticeWriteFrm";
    }

    //공지사항 등록
    @PostMapping("/insertNotice")
    public String insertNotice(NoticeInsertDto notice, MultipartHttpServletRequest mRequest, Model model, HttpServletRequest request) {
        MultipartFile file = mRequest.getFile("file");
        String uploadDir = request.getSession().getServletContext().getRealPath("/upload/notice/");

        // 파일 업로드
        String[] uploadResult = fileUpload(file, uploadDir);

        noticeService.insertNotice(new NoticeInsertDto().builder()
                .nTitle(notice.getNTitle())
                .nContent(notice.getNContent())
                .filename(uploadResult[0])
                .filepath(uploadResult[1])
                .build()
                .toEntity());

        String msg = CommonEnum.NOTICE_INSERT.getVal();
        String loc = "/noticeListPage";
        //String loc = CommonEnum.HOME_URL.getVal();

        return MsgRedirectHelper.success(model,msg,loc);
    }

    private String[] fileUpload(MultipartFile file, String uploadDir) {
        if (file == null || file.isEmpty()) {
            return new String[]{null, null};
        }

        try {
            String filename = file.getOriginalFilename();
            String filepath = CommonUtil.fileRename(uploadDir, filename);
            Path fullPath = Paths.get(uploadDir, filepath);

            Files.createDirectories(fullPath.getParent());

            try (BufferedOutputStream bos = new BufferedOutputStream(Files.newOutputStream(fullPath))) {
                bos.write(file.getBytes());
            }


            return new String[]{filename, filepath};

        } catch (IOException e) {
            e.printStackTrace();
            return new String[]{null, null};
        }
    }

/*
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

