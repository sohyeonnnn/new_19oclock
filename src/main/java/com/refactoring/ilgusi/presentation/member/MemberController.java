package com.refactoring.ilgusi.presentation.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@Controller
public class MemberController {

    @GetMapping({"/join"})
    public String joinFrm() {
        return "/member/joinFrm";
    }

    @GetMapping("/forgotIdPw")
    public String searchIdPwFrm() {
        return "member/searchIdPw";
    }

    @GetMapping("/login")
    public String login() {
        return "member/searchIdPw";
    }

    @GetMapping("/logout")
    public String logout() {
        return "member/searchIdPw";
    }

    
}

