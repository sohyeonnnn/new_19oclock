package com.refactoring.ilgusi.common;

import com.refactoring.ilgusi.domain.member.Member;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Component
@ControllerAdvice
public class GlobalModelAttributeAdvice {

    //자동으로 model에 로그인정보 세팅
    @ModelAttribute
    public void addLoginAttributes(HttpSession session, Model model) {
        Optional.ofNullable((Member) session.getAttribute("loginMember"))
                .ifPresent(member -> {
                    model.addAttribute("loginMember", member);
                    model.addAttribute("isAdmin", member.isAdmin());
                    model.addAttribute("isUser", member.isUser());
                    model.addAttribute("isFreelancer", member.isFreelancer());
                });
    }
}
