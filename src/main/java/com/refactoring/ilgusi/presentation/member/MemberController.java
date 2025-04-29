package com.refactoring.ilgusi.presentation.member;

import com.refactoring.ilgusi.common.CommonEnum;
import com.refactoring.ilgusi.common.MsgRedirectHelper;
import com.refactoring.ilgusi.common.ResultData;
import com.refactoring.ilgusi.domain.member.Member;
import com.refactoring.ilgusi.domain.member.dto.MemberLoginDto;
import com.refactoring.ilgusi.domain.member.dto.MemberUpdateDto;
import com.refactoring.ilgusi.domain.member.interfaces.MemberService;
import com.refactoring.ilgusi.domain.member.RoleEnum;
import com.refactoring.ilgusi.domain.member.dto.MemberJoinDto;
import com.refactoring.ilgusi.domain.member.dto.MemberSearchIdPwDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@RequiredArgsConstructor
@Controller
public class MemberController {
    private final MemberService memberService;

    // 회원가입 페이지 이동
    @GetMapping("/join")
    public String joinFrm() {
        return "member/joinFrm";
    }

    // 아이디/비번 찾기 페이지 이동
    @GetMapping("/forgotIdPw")
    public String searchIdPwFrm() {
        return "member/searchIdPw";
    }

    // 아이디 중복검사
    @ResponseBody
    @GetMapping("/checkDupId")
    public ResponseEntity<?> checkDuplicateId(@RequestParam String id) {
        return ResponseEntity.ok(ResultData.builder().isSuccess(memberService.checkDupId(id)).build());
    }

    // 회원가입 기능
    @RequestMapping("/register")
    public String register(MemberJoinDto m, Model model) {
        memberService.registerMember(m.toEntity());

        String msg = CommonEnum.JOIN.getVal();
        String loc = CommonEnum.HOME_URL.getVal();

        return MsgRedirectHelper.success(model,msg,loc);
    }

    // 로그인
    @PostMapping("/login")
    public String login(HttpServletRequest req, MemberLoginDto m, Model model) {
        String msg = CommonEnum.LOGIN.getVal();
        String loc = "/";
        Member loginMember = memberService.loginMember(m.getMId(), m.getMPw());

        HttpSession session = req.getSession();
        session.setAttribute("loginMember", loginMember);
        if (loginMember.getMGrade().equals(RoleEnum.ADMIN)) {
            loc = "/manageMember?reqPage=1&grade=all&keyword=&order=new";
        }
        return MsgRedirectHelper.success(model,msg,loc);
    }

    // 로그아웃
    @PostMapping("/logout")
    public void logout(HttpServletRequest req, Model model) {
        req.getSession().setAttribute("loginMember", null);
/*
        String msg = CommonEnum.LOGOUT.getVal();
        String loc = CommonEnum.HOME_URL.getVal();

        return MsgRedirectHelper.success(model,msg,loc);*/
    }

    // 아이디 찾기
    @PostMapping("/searchId")
    public String searchId(MemberSearchIdPwDto m, Model model) {
        Member member = memberService.searchId(m.toEntity());

        String msg = CommonEnum.ID_IS.getVal() + member.getMId();
        String loc = "/";

        return MsgRedirectHelper.build(model,msg,loc);
    }

    // 비번 찾기
    @PostMapping("/searchPw")
    public String resetPw(HttpServletRequest req, MemberSearchIdPwDto m, Model model) {
        String msg = CommonEnum.PW_IS.getVal() +  memberService.resetPw(m.toEntity());
        String loc = "/";

        return MsgRedirectHelper.build(model,msg,loc);
    }

    // 비번 찾기 (비번 변경)
    @PostMapping("/searchChangePw")
    public String searchPw(HttpServletRequest req, String mPw, Model model) {
        Member m = (Member) req.getSession().getAttribute("loginMember");
        memberService.changePw(m, mPw);

        String msg = CommonEnum.UPDATE_SUCCESS.getVal();
        String loc = "/";

        return MsgRedirectHelper.close(model,msg,loc, true);
    }

    // 사용자 마이페이지 이동
    @RequestMapping("/userMypage")
    public String userMypage(@ModelAttribute("loginMember")Member m) {
        if (m.getMGrade().equals(RoleEnum.USER)) {
            return "member/userMypage";
        } else if (m.getMGrade().equals(RoleEnum.FREELANCER)) {
            return "redirect:/freelancerMypage?MNo=" + m.getMNo();
        }
        return "/";
    }

    //  마이페이지에서 사용자-프리랜서 전환
    @RequestMapping("/changeGrade")
    public String changeGrade(@ModelAttribute("loginMember")Member m, HttpServletRequest req) {
        Member member = memberService.changeGrade(m.getMId());
        req.getSession().setAttribute("loginMember", member);
        if (member.getMGrade() == RoleEnum.USER) {
            return "member/userMypage";
        } else {
            return "redirect:/freelancerMypage?MNo=" + member.getMNo();
        }
    }

    // 사용자 마이페이지-이메일, 폰번호 변경
    @ResponseBody
    @RequestMapping("/changeMypage")
    public String changeMypage(MemberUpdateDto m, HttpServletRequest req) {
        Member updatedMember = memberService.changeMypage(m.getMId(), m.getData(), m.getObject());
        req.getSession().setAttribute("loginMember", updatedMember);
        return "/";
    }


    // 사용자 마이페이지 - 회원탈퇴
    @RequestMapping("/deleteMember")
    public String deleteMember(@ModelAttribute("loginMember")Member sessionMember, Integer mNo, HttpServletRequest req, Model model) {
        String msg = "탈퇴 되었습니다.";
        String loc= "/";
        memberService.tradeStatus(mNo);
        if (!sessionMember.isAdmin()) {
            memberService.setDeleteStatusY(mNo); // 서비스 delete_status = 'y'로 바꿈
            memberService.deleteMember(mNo);
            req.getSession().setAttribute("loginMember", null);
        } else {// 관리자가 삭제할때
            //memberService.setDeleteStatusY(mNo); // delete_status = 'y'로 바꿈*/
            //memberService.deleteMember(mNo);
            loc = "/manageMember?reqPage=1&grade=black&keyword=&order=new";
        }
        return MsgRedirectHelper.build(model,msg,loc);
    }

    @PostMapping("/checkPassword")
    @ResponseBody
    public ResponseEntity<?> checkPassword(@RequestParam String mId, @RequestParam String mPw) {
        // DB에서 암호화된 비밀번호 가져오기
        memberService.loginMember(mId, mPw);
        return ResponseEntity.ok(ResultData.builder().isSuccess(true).build());
    }



/*
    // 사용자 마이페이지-비밀번호 변경
    @RequestMapping("/changePw.do")
    public String changePw(String mId, String mPw, String data, String object, HttpServletRequest req) {
        int result = service.changeMypage(mId, data, object);
        if (result > 0) {
            Member m = service.loginMember(mId, data);
            if (m != null) {
                HttpSession session = req.getSession();
                session.setAttribute("loginMember", m);
            }
        }
        return "member/userMypage";
    }



    // 마이페이지 - 의뢰내역 확인하기
    @RequestMapping("/userRequestHistory.do")
    public String userRequestHistory( int reqPage, Model model) {
        RequestPageData rpd = reqService.selectRequestList(reqPage, "new", "all", null,"mypage");
        model.addAttribute("list", rpd.getList());
        model.addAttribute("pageNavi", rpd.getPageNavi());
        model.addAttribute("totalCount", rpd.getTotalCount());
        return "member/userRequestHistory";
    }
*/

    
}

