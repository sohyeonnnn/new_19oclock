package com.refactoring.ilgusi.presentation.member;

import com.refactoring.ilgusi.common.CommonEnum;
import com.refactoring.ilgusi.common.MsgRedirectHelper;
import com.refactoring.ilgusi.common.ResultData;
import com.refactoring.ilgusi.domain.member.Member;
import com.refactoring.ilgusi.domain.member.RoleEnum;
import com.refactoring.ilgusi.domain.member.dto.MemberJoinDto;
import com.refactoring.ilgusi.domain.member.dto.MemberLoginDto;
import com.refactoring.ilgusi.domain.member.dto.MemberSearchIdPwDto;
import com.refactoring.ilgusi.domain.member.dto.MemberUpdateDto;
import com.refactoring.ilgusi.domain.member.interfaces.MemberService;
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

    // 사용자 마이페이지 이동
    @GetMapping("/userMypage")
    public String userMypage(@ModelAttribute("loginMember")Member member) {
        if (member.getMemberGrade() == RoleEnum.USER) {
            return "member/userMypage";
        } else if (member.getMemberGrade() == RoleEnum.FREELANCER) {
            return "redirect:/freelancerMypage";
        }
        return CommonEnum.HOME_URL.getVal();
    }

    // 아이디 중복검사
    @ResponseBody
    @PostMapping("/checkDupId")
    public ResponseEntity<?> checkDuplicateId(@RequestParam String id) {
        return ResponseEntity.ok(ResultData.builder().isSuccess(memberService.checkDupId(id)).build());
    }

    // 회원가입
    @PostMapping("/register")
    public String register(MemberJoinDto member, Model model) {
        memberService.registerMember(member.toEntity());

        String msg = CommonEnum.JOIN.getVal();
        String loc = CommonEnum.HOME_URL.getVal();

        return MsgRedirectHelper.success(model,msg,loc);
    }

    // 로그인
    @PostMapping("/login")
    public String login(HttpServletRequest req, MemberLoginDto member, Model model) {
        String msg = CommonEnum.LOGIN.getVal();
        String loc = CommonEnum.HOME_URL.getVal();

        Member loginMember = memberService.loginMember(member.getMemberId(), member.getMemberPw());

        HttpSession session = req.getSession();
        session.setAttribute("loginMember", loginMember);
        if (loginMember.getMemberGrade() == RoleEnum.ADMIN) {
            loc = "/manageMember?reqPage=1&grade=all&keyword=&order=new";
        }
        return MsgRedirectHelper.success(model,msg,loc);
    }

    // 로그아웃
    @PostMapping("/logout")
    public void logout(HttpServletRequest req, Model model) {
        req.getSession().setAttribute("loginMember", null);
    }

    // 아이디 찾기
    @PostMapping("/searchId")
    public String searchId(MemberSearchIdPwDto m, Model model) {
        Member member = memberService.searchId(m.toEntity());

        String msg = CommonEnum.ID_IS.getVal() + member.getMemberId();
        String loc = CommonEnum.HOME_URL.getVal();

        return MsgRedirectHelper.build(model,msg,loc);
    }

    // 비번 찾기
    @PostMapping("/searchPw")
    public String resetPw(HttpServletRequest req, MemberSearchIdPwDto m, Model model) {
        String msg = CommonEnum.PW_IS.getVal() +  memberService.resetPw(m.toEntity());
        String loc = CommonEnum.HOME_URL.getVal();

        return MsgRedirectHelper.build(model,msg,loc);
    }

    // 비번 찾기 (비번 변경)
    @PostMapping("/searchChangePw")
    public String searchPw(HttpServletRequest req, String mPw, Model model) {
        Member m = (Member) req.getSession().getAttribute("loginMember");
        memberService.changePw(m, mPw);

        String msg = CommonEnum.UPDATE_SUCCESS.getVal();
        String loc = CommonEnum.HOME_URL.getVal();

        return MsgRedirectHelper.close(model,msg,loc, true);
    }

    //  마이페이지에서 사용자-프리랜서 전환
    @PostMapping("/changeGrade")
    public String changeGrade(@ModelAttribute("loginMember")Member m, HttpServletRequest req) {
        Member member = memberService.changeGrade(m.getMemberNo());
        req.getSession().setAttribute("loginMember", member);
        if (member.getMemberGrade() == RoleEnum.USER) {
            return "member/userMypage";
        } else {
            return "redirect:/freelancerMypage";
        }
    }

    // 사용자 마이페이지-이메일, 폰번호 변경
    @ResponseBody
    @RequestMapping("/changeMypage")
    public String changeMypage(MemberUpdateDto m, HttpServletRequest req) {
        Member updatedMember = memberService.changeMypage(m.getMemberNo(), m.getData(), m.getObject());
        req.getSession().setAttribute("loginMember", updatedMember);
        return CommonEnum.HOME_URL.getVal();
    }

    // 사용자 마이페이지-비밀번호 변경
    @PostMapping("/changePw")
    public String changePw(@RequestParam Integer mNo, @RequestParam String data, @RequestParam String object, HttpServletRequest req) {
        Member updatedMember = memberService.changeMypage(mNo, data, object);
        req.getSession().setAttribute("loginMember", updatedMember);
        return "member/userMypage";
    }

    // 사용자 마이페이지 - 회원탈퇴
    @RequestMapping("/deleteMember")
    public String deleteMember(@ModelAttribute("loginMember")Member sessionMember, Integer memberNo, HttpServletRequest req, Model model) {
        String msg = "탈퇴 되었습니다.";
        String loc= CommonEnum.HOME_URL.getVal();
        memberService.unregisterMember(memberNo);
        req.getSession().setAttribute("loginMember", null);
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

    // 마이페이지 - 의뢰내역 확인하기
    @RequestMapping("/userRequestHistory")
    public String userRequestHistory( int reqPage, Model model) {
        RequestPageData rpd = reqService.selectRequestList(reqPage, "new", "all", null,"mypage");
        model.addAttribute("list", rpd.getList());
        model.addAttribute("pageNavi", rpd.getPageNavi());
        model.addAttribute("totalCount", rpd.getTotalCount());
        return "member/userRequestHistory";
    }


    List<Map<String, Object>> mustacheList = new ArrayList<>();
for (int i = 0; i < list.size(); i++) {
    Request r = list.get(i);
    if (r.getMId().equals(loginMember.getMId())) {
        Map<String, Object> map = new HashMap<>();
        map.put("index", i + 1);
        map.put("reqNo", r.getReqNo());
        map.put("reqTitle", r.getReqTitle());
        map.put("writeDate", r.getWriteDate());
        map.put("isOwner", true); // MId 비교 결과
        map.put("isRecruiting", r.getReqStatus() == 0);
        map.put("isOngoing", r.getReqStatus() == 1);
        map.put("isClosed", r.getReqStatus() == 2);
        mustacheList.add(map);
    }
}
model.addAttribute("list", mustacheList);









memberlist
model.addAttribute("memberList", memberList);
model.addAttribute("isFreePage", page.equals("free"));
model.addAttribute("isBlackPage", page.equals("black"));
model.addAttribute("orderNew", order.equals("new"));
model.addAttribute("orderBlack", order.equals("black"));
model.addAttribute("notAdmin", !m.getMId().equals("admin"));
model.addAttribute("hasHistory", useHistory != null && useHistory.containsKey(m.getMNo()));
// etc.
*/

    
}

