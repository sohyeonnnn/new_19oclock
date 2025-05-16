package com.refactoring.ilgusi.presentation.member;

import com.refactoring.ilgusi.domain.member.Member;
import com.refactoring.ilgusi.domain.member.dto.FreelancerUpdateDto;
import com.refactoring.ilgusi.domain.member.interfaces.FreelancerService;
import com.refactoring.ilgusi.domain.member.interfaces.MemberService;
import com.refactoring.ilgusi.domain.service.interfaces.ServiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;


@RequiredArgsConstructor
@Controller
public class FreelancerController {
    private final MemberService memberService;
    private final FreelancerService freelancerService;
    private final ServiceService serviceService;

    @RequestMapping("/freelancerMypage")
    public String selectFreelancerMypage(@ModelAttribute("loginMember")Member member, Model model) {

        //int serviceCount = service.selectFreeServiceCount(m.getMId());
        //model.addAttribute("serviceCount", serviceCount);
        return "freelancer/freelancerMypage";
    }

    // 프리랜서 마이페이지 정보수정(소개글,연락가능시간,브랜드명 추가)
    @PostMapping("/updateFreelancer")
    public String updateFreelancer(FreelancerUpdateDto m, Model model, HttpServletRequest req) {
        Member member = memberService.updateFreelancer(m.toEntity());
        req.getSession().setAttribute("loginMember", member);
        return "redirect:/freelancerMypage";
    }

    @GetMapping("/introduceFrm")
    public String introduceFrm(@ModelAttribute("loginMember") Member m, int reqPage, Model model) {
        /*FreelancerIntroduceDto introduce = freelancerService.selectFreelancerIntroduce(m.getMemberNo());
        System.out.println(introduce.toString());
*/
        /*
        j.setServiceList(approvedList);
        // 후기리스트

        Join join = new Join();
        if (service.selectReviewList(mId, reqPage) != null) {
            join = service.selectReviewList(mId, reqPage);
            j.setReviewList(join.getReviewList());
        }

        float avg = service.sRateAVG(mId);
        model.addAttribute("avg", avg);
        model.addAttribute("pageNavi", join.getPageNavi());
        model.addAttribute("j", j);*/
        return "freelancer/introduce";
    }
    
}

