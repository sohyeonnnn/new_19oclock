package com.refactoring.ilgusi.domain.member.interfaces;

import com.refactoring.ilgusi.domain.member.Member;
import com.refactoring.ilgusi.domain.member.RoleEnum;

import java.util.Optional;

// 모든 DB 기술에서 공통으로 사용할 비즈니스 인터페이스
public interface MemberRepository  {

    // 회원가입 기능
    Member saveMember(Member member);

    // 아이디 중복검사 기능
    Optional<Member> findByMemberId(String memberId);

    Optional<Member> findByMemberNo(Integer memberNo);

    // 아이디/비밀번호 찾기 기능
    Optional<Member> searchIdPw(Member member);

    // 비밀번호 찾기 후 - 비밀번호 변경
    int changePw(Member member);

    // 사용자 마이페이지-이메일, 폰번호, 비번 변경
    Optional<Member> changeMypage(int memberNo, String data, String object);

    // 사용자 마이페이지-회원탈퇴
   void deleteMember(int memberNo);

    // 마이페이지에서 사용자-프리랜서 전환
    Optional<Member> changeGrade(int memberNo, RoleEnum user, RoleEnum free);

    // 로그인하면 grade를 1로 셋팅
    void settingMemberGrade(Member member);


    Optional<Member> updateFreelancer(Member member);
}
