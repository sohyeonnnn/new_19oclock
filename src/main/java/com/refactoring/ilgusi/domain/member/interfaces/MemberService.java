package com.refactoring.ilgusi.domain.member.interfaces;

import com.refactoring.ilgusi.domain.member.Member;

public interface MemberService {
    int selectBuyingCount(int mNo);
    int selectSellingCount(String mId);
    Member loginMember(String id, String pw);
    Member selectOneMember(Integer no);
    void registerMember(Member m);
    boolean checkDupId(String id);
    Member searchId(Member m);
    String resetPw(Member m);
    void changePw(Member m, String newPw);
    Member changeMypage(String mId, String data, String object);
    Member deleteMember(String mId);
    Member changeGrade(String mId);
    void settingMemberGrade(Member m);
    void tradeStatus(int mNo);
    Member setDeleteStatusY(String mId);
    Member updateFreelancer(Member m);
}
