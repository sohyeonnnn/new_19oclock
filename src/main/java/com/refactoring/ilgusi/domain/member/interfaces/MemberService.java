package com.refactoring.ilgusi.domain.member.interfaces;

import com.refactoring.ilgusi.domain.member.Member;

public interface MemberService {
    Member loginMember(String id, String pw);
    Member selectOneMember(Integer mNo);
    void registerMember(Member m);
    boolean checkDupId(String id);
    Member searchId(Member m);
    String resetPw(Member m);
    void changePw(Member m, String newPw);
    Member changeMypage(int mNo, String data, String object);
    void unregisterMember(int mNo);
    Member changeGrade(int mNo);
    Member updateFreelancer(Member m);
}
