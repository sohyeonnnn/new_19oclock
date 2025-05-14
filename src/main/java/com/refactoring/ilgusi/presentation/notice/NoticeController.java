package com.refactoring.ilgusi.presentation.notice;

import com.refactoring.ilgusi.common.CommonEnum;
import com.refactoring.ilgusi.common.CommonUtil;
import com.refactoring.ilgusi.common.MsgRedirectHelper;
import com.refactoring.ilgusi.domain.notice.dto.NoticeInsertDto;
import com.refactoring.ilgusi.domain.notice.dto.NoticePageDto;
import com.refactoring.ilgusi.domain.notice.dto.NoticeUpdateDto;
import com.refactoring.ilgusi.domain.notice.dto.NoticeViewDto;
import com.refactoring.ilgusi.domain.notice.interfaces.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.File;
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
    public ResponseEntity<NoticePageDto> noticeListApi (Integer reqPage, String keyword) {
        return ResponseEntity.ok(noticeService.selectNoticeListApi(reqPage, keyword));
    }

    //공지사항 작성
    @GetMapping("/noticeWriteFrm")
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

        noticeService.insertNotice(NoticeInsertDto.builder()
                .noticeTitle(notice.getNoticeTitle())
                .noticeContent(notice.getNoticeContent())
                .filename(uploadResult[0])
                .filepath(uploadResult[1])
                .build()
                .toEntity());

        String msg = CommonEnum.NOTICE_INSERT.getVal();
        String loc = "/noticeListPage";

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



    //공지사항 내용 보기
    @GetMapping("/noticeView")
    public String noticeView (Integer noticeNo, Model model) {
        NoticeViewDto noticeView = noticeService.selectNoticeView(noticeNo);
        model.addAttribute("notice", noticeView);
        return "notice/noticeView";
    }

    //공지사항 삭제
    @PostMapping("/deleteNotice")
    public String deleteNotice(Integer noticeNo, Model model) {
        noticeService.deleteNotice(noticeNo);

        String msg = CommonEnum.NOTICE_DELETE.getVal();
        String loc = "/noticeListPage";

        return MsgRedirectHelper.success(model,msg,loc);
    }

    @PostMapping("/updateNoticeFrm")
    public String updateNoticeFrm (Integer noticeNo, Model model) {
        NoticeViewDto noticeView = noticeService.selectNoticeView(noticeNo);
        model.addAttribute("notice", noticeView);

        return "notice/noticeUpdateFrm";
    }

    // 공지사항 수정
    @PostMapping("/updateNotice")
    public String updateNotice(NoticeUpdateDto notice, MultipartHttpServletRequest mRequest, Model model, HttpServletRequest request) {
        MultipartFile file = mRequest.getFile("file");
        String uploadDir = request.getSession().getServletContext().getRealPath("/upload/notice/");

        // 기존 notice 정보 조회 (기존 파일 정보 확보용)
        NoticeViewDto oldNotice = noticeService.selectNoticeView(notice.getNoticeNo());

        String filename = oldNotice.getFilename();
        String filepath = oldNotice.getFilepath();

        if (file != null && !file.isEmpty()) {
            // 기존 파일 삭제
            if (filepath != null) {
                File oldFile = new File(filepath);
                if (oldFile.exists()) {
                    oldFile.delete(); // 삭제 시도
                }
            }

            // 새 파일 업로드
            String[] uploadResult = fileUpload(file, uploadDir);
            filename = uploadResult[0];
            filepath = uploadResult[1];
        }

        noticeService.updateNotice(NoticeUpdateDto.builder()
                .noticeNo(notice.getNoticeNo())
                .noticeTitle(notice.getNoticeTitle())
                .noticeContent(notice.getNoticeContent())
                .filename(filename)
                .filepath(filepath)
                .build()
                .toEntity());

        String msg = CommonEnum.NOTICE_UPDATE.getVal();
        String loc = "/noticeListPage";

        return MsgRedirectHelper.success(model, msg, loc);
    }

    //관리자 공지사항리스트
  /*  @RequestMapping("/adminNoticeList")
    public String adminNoticeList ( Model model, int reqPage, String keyword) {
        NoticePageData npd = noticeService.selectNoticeList(reqPage,keyword);
        model.addAttribute("list", npd.getList());
        model.addAttribute("pageNavi", npd.getPageNavi());

        return "admin/noticeList";
    }*/


}

