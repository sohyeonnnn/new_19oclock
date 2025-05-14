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

    // 아이디 중복검사
    @ResponseBody
    @PostMapping("/checkDupId")
    public ResponseEntity<?> checkDuplicateId(@RequestParam String id) {
        return ResponseEntity.ok(ResultData.builder().isSuccess(memberService.checkDupId(id)).build());
    }

    // 회원가입
    @PostMapping("/register")
    public String register(MemberJoinDto joinMemberDto, Model model) {
        memberService.registerMember(joinMemberDto.toEntity());

        String msg = CommonEnum.JOIN.getVal();
        String loc = CommonEnum.HOME_URL.getVal();

        return MsgRedirectHelper.success(model,msg,loc);
    }

    // 로그인
    @PostMapping("/login")
    public String login(HttpServletRequest req, MemberLoginDto loginMemberDto, Model model) {
        String msg = CommonEnum.LOGIN.getVal();
        String loc = CommonEnum.HOME_URL.getVal();

        Member loginMember = memberService.loginMember(loginMemberDto.getMemberId(), loginMemberDto.getMemberPw());
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

    // 아이디/비번 찾기 페이지 이동
    @GetMapping("/forgotIdPw")
    public String searchIdPwFrm() {
        return "member/searchIdPw";
    }

    // 아이디 찾기
    @PostMapping("/searchId")
    public String searchId(MemberSearchIdPwDto searchIdDto, Model model) {
        Member member = memberService.searchId(searchIdDto.toEntity());

        String msg = CommonEnum.ID_IS.getVal() + member.getMemberId();
        String loc = CommonEnum.HOME_URL.getVal();

        return MsgRedirectHelper.build(model,msg,loc);
    }

    // 비번 찾기
    @PostMapping("/resetPassword")
    public String resetPw(HttpServletRequest req, MemberSearchIdPwDto searchPwDto, Model model) {
        String msg = CommonEnum.PW_IS.getVal() +  memberService.resetPw(searchPwDto.toEntity());
        String loc = CommonEnum.HOME_URL.getVal();

        return MsgRedirectHelper.build(model,msg,loc);
    }

    // 사용자 마이페이지 이동
    @GetMapping("/userMypage")
    public String userMypage(@ModelAttribute("loginMember")Member loginMember) {
        if (loginMember.getMemberGrade() == RoleEnum.USER) {
            return "member/userMypage";
        } else if (loginMember.getMemberGrade() == RoleEnum.FREELANCER) {
            return "redirect:/freelancerMypage";
        }
        return CommonEnum.HOME_URL.getVal();
    }

    // 비번 변경
    @PostMapping("/changePassword")
    public String searchPw(@ModelAttribute("loginMember")Member loginMember, HttpServletRequest req, String memberPw, Model model) {
        memberService.changePw(loginMember, memberPw);

        String msg = CommonEnum.UPDATE_SUCCESS.getVal();
        String loc = CommonEnum.HOME_URL.getVal();

        return MsgRedirectHelper.close(model,msg,loc, true);
    }

    //  마이페이지 사용자-프리랜서 전환
    @PostMapping("/changeGrade")
    public String changeGrade(@ModelAttribute("loginMember")Member loginMember, HttpServletRequest req) {
        Member updatedMember = memberService.changeGrade(loginMember.getMemberNo());
        req.getSession().setAttribute("loginMember", updatedMember);
        if (updatedMember.getMemberGrade() == RoleEnum.USER) {
            return "member/userMypage";
        } else {
            return "redirect:/freelancerMypage";
        }
    }

    // 사용자 마이페이지-이메일, 폰번호 변경
    @ResponseBody
    @RequestMapping("/updateInfo")
    public String changeMypage(MemberUpdateDto updateMemberDto, HttpServletRequest req) {
        Member updatedMember = memberService.changeMypage(updateMemberDto.getMemberNo(), updateMemberDto.getData(), updateMemberDto.getObject());
        req.getSession().setAttribute("loginMember", updatedMember);
        return CommonEnum.HOME_URL.getVal();
    }

    // 사용자 마이페이지-비밀번호 변경
    @PostMapping("/updatePassword")
    public String updatePassword(@RequestParam Integer memberNo, @RequestParam String data, @RequestParam String object, HttpServletRequest req) {
        Member updatedMember = memberService.changeMypage(memberNo, data, object);
        req.getSession().setAttribute("loginMember", updatedMember);
        return "member/userMypage";
    }

    // 사용자 마이페이지 - 회원탈퇴
    @RequestMapping("/deleteMember")
    public String deleteMember(@ModelAttribute("loginMember")Member loginMember, HttpServletRequest req, Model model) {
        String msg = "탈퇴 되었습니다.";
        String loc= CommonEnum.HOME_URL.getVal();
        memberService.unregisterMember(loginMember.getMemberNo());
        req.getSession().setAttribute("loginMember", null);
        return MsgRedirectHelper.build(model,msg,loc);
    }

    @PostMapping("/checkPassword")
    @ResponseBody
    public ResponseEntity<?> checkPassword(@RequestParam String memberId, @RequestParam String memberPw) {
        // DB에서 암호화된 비밀번호 가져오기
        memberService.loginMember(memberId, memberPw);
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

