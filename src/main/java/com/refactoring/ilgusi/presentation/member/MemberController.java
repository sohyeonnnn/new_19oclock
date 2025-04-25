package com.refactoring.ilgusi.presentation.member;

import com.refactoring.ilgusi.common.CommonEnum;
import com.refactoring.ilgusi.common.MsgRedirectHelper;
import com.refactoring.ilgusi.common.ResultData;
import com.refactoring.ilgusi.domain.member.Member;
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
    public String login(HttpServletRequest req, String id, String pw, String loc, Model model) {
        String msg = CommonEnum.LOGIN.getVal();
        Member m = memberService.loginMember(id, pw);

        HttpSession session = req.getSession();
        session.setAttribute("loginMember", m);
        System.err.println("logimMember : "+m.toString());
        if (m.getMGrade().equals(RoleEnum.ADMIN)) {
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
        HttpSession session = req.getSession();

        Member m = (Member) session.getAttribute("loginMember");
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
        if (m == null) {
            return "redirect:/login";
        }

        Member member = memberService.changeGrade(m.getMId());
        System.out.println("전환 후 멤버정보 : "+member.toString());
        if (member == null || member.getMGrade() == null) {
            throw new IllegalStateException("회원 등급 변경 실패 또는 등급 정보 없음");
        }

        req.getSession().setAttribute("loginMember", member);

        if (member.getMGrade() == RoleEnum.USER) {
            return "member/userMypage";
        } else {
            return "redirect:/freelancerMypage?MNo=" + member.getMNo();
        }
    }



/*

    // 사용자 마이페이지-이메일, 폰번호 변경
    @ResponseBody
    @RequestMapping("/changeMypage.do")
    public String changeMypage(String mId, String mPw, String data, String object, HttpServletRequest req) {
        int result = service.changeMypage(mId, data, object);
        if (result > 0) {
            Member m = service.loginMember(mId, mPw);
            if (m != null) {
                HttpSession session = req.getSession();
                session.setAttribute("loginMember", m);
            }
        }
        return "";
    }

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

    // 사용자 마이페이지 - 회원탈퇴(아이디로만)
    @RequestMapping("/deleteMember.do")
    public String deleteMember(String mId, HttpServletRequest req, Model model, String admin, String mNo) {
        if (mNo == null) {
            // 회원번호 받아서 거래중인 것이 있는지 확인 -> 없어야만 삭제
            HttpSession session = req.getSession();
            Member m = (Member) session.getAttribute("loginMember");
            int tradeStatus = service.tradeStatus(m.getMNo());

            // 탈퇴 진행
            if (tradeStatus == 0) {
                int result = service.setDeleteStatusY(mId); // delete_status = 'y'로 바꿈
                result = service.deleteMember(mId);
                System.out.println("사용자 탈퇴성공");
                model.addAttribute("loc", "/userMypage.do");
                session.setAttribute("loginMember", null);
                model.addAttribute("msg", "탈퇴 되었습니다.");
                model.addAttribute("loc", "/");
            }
            // 거래중인 서비스가 있어서 탈퇴 거절
            else {
                model.addAttribute("msg", "거래 중인 서비스가 있기 때문에 탈퇴하실 수 없습니다.");
                model.addAttribute("loc", "/userMypage.do");
            }
            return "common/msg";
        } else {// 관리자가 삭제할때
            int mNoInt = Integer.parseInt(mNo);
            int tradeStatus = service.tradeStatus(mNoInt);
            // 탈퇴 진행
            if (tradeStatus == 0) {
                int result = service.setDeleteStatusY(mId); // delete_status = 'y'로 바꿈
                result = service.deleteMember(mId);
                System.out.println("관리자 탈퇴");
                model.addAttribute("msg", "탈퇴 되었습니다.");
                model.addAttribute("loc", "manageMember.do?reqPage=1&grade=black&keyword=&order=new");
            }
            // 거래중인 서비스가 있어서 탈퇴 거절
            else {
                model.addAttribute("msg", "거래 중인 서비스가 있기 때문에 탈퇴하실 수 없습니다.");
                model.addAttribute("loc", "/manageMember.do?reqPage=1&grade=black&keyword=&order=new");
            }
            return "common/msg";

        }
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

