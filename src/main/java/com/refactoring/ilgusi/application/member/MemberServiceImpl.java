package com.refactoring.ilgusi.application.member;


import com.refactoring.ilgusi.common.CommonEnum;
import com.refactoring.ilgusi.common.CommonUtil;
import com.refactoring.ilgusi.common.EmailService;
import com.refactoring.ilgusi.domain.member.Member;
import com.refactoring.ilgusi.domain.member.interfaces.MemberRepository;

import com.refactoring.ilgusi.domain.member.interfaces.MemberService;
import com.refactoring.ilgusi.domain.member.RoleEnum;
import com.refactoring.ilgusi.domain.service.ServiceTradeRepository;
import com.refactoring.ilgusi.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;
    private final ServiceTradeRepository serviceTradeRepository;
    private final BCryptPasswordEncoder encoder;
    private final EmailService emailService;

    @Override
    public Member loginMember(String id, String pw) {
        return memberRepository.findBymId(id)
                .filter(member -> encoder.matches(pw, member.getMPw()))
                .map(member -> {
                    if (member.getMGrade() != RoleEnum.ADMIN) {
                        member.changeGrade(RoleEnum.USER);
                    }
                    member.changeBuyingCount(selectBuyingCount(member.getMNo()));
                    member.changeSellingCount(selectSellingCount(member.getMId()));
                    return member;
                })
                .orElseThrow(() -> new CustomException(CommonEnum.LOGIN_FAIL.getVal(), CommonEnum.HOME_URL.getVal()));
    }

    @Override
    public Member selectOneMember(Integer no) {
        return memberRepository.findBymNo(no)
                .orElseThrow(() -> new CustomException("전환실패", "member/userMypage"));
    }

    @Override
    public void registerMember(Member m) {
        m.changePw(encoder.encode(m.getMPw()));
        m.changeGrade(RoleEnum.USER);
        try{
            memberRepository.saveMember(m);
        } catch (DataIntegrityViolationException e) {
            throw new CustomException(CommonEnum.JOIN_FAIL.getVal(), "/join");
        }
    }

    @Override
    public boolean checkDupId(String id) {
        return memberRepository.findBymId(id).isPresent();
    }

    @Override
    public Member searchId(Member m) {
        return memberRepository.searchIdPw(m)
                .orElseThrow(() -> new CustomException(CommonEnum.NOT_VALID_USER.getVal(),"/"));
    }

    @Override
    public String resetPw(Member m) {
        String resetPw = generateTempPassword();

        return memberRepository.searchIdPw(m)
                .map(member -> {
                    this.changePw(member, resetPw);
                    return resetPw;
                })
                .orElseThrow(() -> new CustomException(CommonEnum.NOT_VALID_USER.getVal(),"/",true));
    }

    private void sendResetPwEmail(String email, String tempPassword) {
        String subject = CommonEnum.CHANGE_PW_SUBJECT.getVal();
        String body = CommonEnum.PW_IS.getVal() + tempPassword;
        try{
            emailService.sendEmail(email, subject, body);
        }catch (Exception e){
            throw new CustomException(CommonEnum.ERROR.getVal(),"/",true);
        }
    }

    private String generateTempPassword() {
        return CommonUtil.generateTempPassword(8);
    }

    @Override
    public void changePw(Member m, String newPw) {
        m.changePw(encoder.encode(newPw));
        int updated = memberRepository.changePw(m);
        if (updated <= 0) {
            throw new CustomException(CommonEnum.FAIL_CHANGE_PW.getVal(), "/", true);
        }
    }

    @Override
    public int selectBuyingCount(int mNo) {
        return serviceTradeRepository.selectBuyingCount(mNo);
    }

    @Override
    public int selectSellingCount(String mId) {
        return serviceTradeRepository.selectSellingCount(mId);
    }

    @Override
    public Member changeMypage(String mId, String data, String object) {
        return memberRepository.changeMypage(mId, data, object)
                .orElseThrow(() -> new CustomException(CommonEnum.FAIL.getVal(),"/", true));
    }

    @Override
    public void deleteMember(String mId) {
    }


    @Override
    public Member changeGrade(String mId) {
        RoleEnum user = RoleEnum.USER;
        RoleEnum free = RoleEnum.FREELANCER;
        return memberRepository.changeGrade(mId, user, free)
                .orElseThrow(() -> new CustomException(CommonEnum.FAIL.getVal(),"/", true));
    }

    @Override
    public void settingMemberGrade(Member m) {
    }

    @Override
    public void tradeStatus(int mNo) {
    }

    @Override
    public void setDeleteStatusY(String mId) {
    }

    @Override
    public Member updateFreelancer(Member m) {
        return memberRepository.updateFreelancer(m)
                .orElseThrow(() -> new CustomException(CommonEnum.FAIL.getVal(),"/", true));
    }


}
