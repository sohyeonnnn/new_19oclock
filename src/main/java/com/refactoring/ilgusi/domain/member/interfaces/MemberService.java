package com.refactoring.ilgusi.domain.member.interfaces;

import com.refactoring.ilgusi.domain.member.Member;

public interface MemberService {
    Member loginMember(String id, String pw);
    void registerMember(Member member);
    boolean checkDupId(String id);
    Member searchId(Member member);
    String resetPw(Member member);
    void changePw(Member member, String newPw);
    Member changeMypage(int memberNo, String data, String object);
    void unregisterMember(int memberNo);
    Member changeGrade(int memberNo);
    Member updateFreelancer(Member member);
}
