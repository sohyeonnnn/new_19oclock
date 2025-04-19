package com.refactoring.ilgusi.domain.member.interfaces;

import com.refactoring.ilgusi.domain.member.Member;

public interface MemberService {
    int selectBuyingCount(int mNo);
    int selectSellingCount(String mId);
    Member loginMember(String id, String pw);
    void registerMember(Member m);
    boolean checkDupId(String id);
    Member searchId(Member m);
    String resetPw(Member m);
    void changePw(Member m, String newPw);
    void changeMypage(String mId, String data, String object);
    void deleteMember(String mId);
    void changeGrade(String mId, int grade);
    void settingMemberGrade(Member m);
    void tradeStatus(int mNo);
    void setDeleteStatusY(String mId);
}
