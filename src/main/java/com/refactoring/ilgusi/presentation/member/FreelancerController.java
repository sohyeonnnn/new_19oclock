package com.refactoring.ilgusi.presentation.member;

import com.refactoring.ilgusi.common.MsgRedirectHelper;
import com.refactoring.ilgusi.domain.member.Member;
import com.refactoring.ilgusi.domain.member.dto.FreelancerUpdateDto;
import com.refactoring.ilgusi.domain.member.interfaces.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@RequiredArgsConstructor
@Controller
public class FreelancerController {
    private final MemberService memberService;

    @RequestMapping("/freelancerMypage")
    public String selectfreelancerMypage(int MNo, Model model) {
        Member m = memberService.selectOneMember(MNo);
        //int serviceCount = service.selectFreeServiceCount(m.getMId());
        model.addAttribute("m", m);
        if(m.getBrandName()!= null){
            model.addAttribute("hasBrandAndService", true);
        }else{
            model.addAttribute("hasBrandAndService", null);
        }
        //model.addAttribute("serviceCount", serviceCount);
        return "freelancer/freelancerMypage";
    }

    // 프리랜서 마이페이지 정보수정(소개글,연락가능시간,브랜드명 추가)
    @PostMapping("/updateFreelancer")
    public String updateFreelancer(FreelancerUpdateDto m, Model model, HttpServletRequest req) {
        Member member = memberService.updateFreelancer(m.toEntity());
        req.getSession().setAttribute("loginMember", member);
        String msg = "등록되었습니다.";
        String loc = "/freelancerMypage?MNo=" + member.getMNo();
        return MsgRedirectHelper.success(model,msg,loc);
    }
    
}

