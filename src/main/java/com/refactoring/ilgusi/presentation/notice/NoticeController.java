package com.refactoring.ilgusi.presentation.notice;

import com.refactoring.ilgusi.domain.member.Member;
import com.refactoring.ilgusi.domain.notice.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.Optional;


@RequiredArgsConstructor
@Controller
public class NoticeController {
    private final HttpSession httpsession;
    private final NoticeService noticeService;

    @GetMapping("/noticeList")
    public String noticeList(Model model) {
        model.addAttribute("pageNavi",3);
        return "/notice/noticeList";
    }

    @GetMapping("/noticeView")
    public String noticeView(Model model) {
        return "/notice/noticeView";
    }

    @GetMapping("/noticeWriteFrm")
    public String noticeWriteFrm(Model model) {
        return "/notice/noticeWriteFrm";
    }


}

