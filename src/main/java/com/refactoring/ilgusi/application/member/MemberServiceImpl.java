package com.refactoring.ilgusi.application.member;


import com.refactoring.ilgusi.common.CommonEnum;
import com.refactoring.ilgusi.common.CommonUtil;
import com.refactoring.ilgusi.common.EmailService;
import com.refactoring.ilgusi.domain.member.Member;
import com.refactoring.ilgusi.domain.member.RoleEnum;
import com.refactoring.ilgusi.domain.member.interfaces.MemberRepository;
import com.refactoring.ilgusi.domain.member.interfaces.MemberService;
import com.refactoring.ilgusi.domain.service.interfaces.ServiceRepository;
import com.refactoring.ilgusi.domain.service.interfaces.ServiceTradeRepository;
import com.refactoring.ilgusi.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;
    private final ServiceTradeRepository serviceTradeRepository;
    private final ServiceRepository serviceRepository;
    private final BCryptPasswordEncoder encoder;
    private final EmailService emailService;

    @Override
    public Member loginMember(String id, String pw) {
        return memberRepository.findBymId(id)
                .filter(member -> encoder.matches(pw, member.getMPw()))
                .map(member -> {
                    /*if (member.getMGrade() != RoleEnum.ADMIN) {
                        member.setGrade(RoleEnum.USER);
                    }*/
                    member.setBuyingCount(selectBuyingCount(member.getMNo()));
                    member.setSellingCount(selectSellingCount(member.getMNo()));
                    return member;
                })
                .orElseThrow(() -> new CustomException(CommonEnum.FAIL.getVal(), CommonEnum.HOME_URL.getVal()));
    }

    public int selectBuyingCount(int mNo) {
        return serviceTradeRepository.selectBuyingCount(mNo);
    }

    public int selectSellingCount(int mNo) {
        return serviceTradeRepository.selectSellingCount(mNo);
    }

    @Override
    public Member selectOneMember(Integer no) {
        return memberRepository.findBymNo(no)
                .orElseThrow(() -> new CustomException("사용자가 없습니다.", "member/userMypage"));
    }

    @Override
    @Transactional
    public void registerMember(Member m) {
        m.setPw(encoder.encode(m.getMPw()));
        m.setGrade(RoleEnum.USER);
        if(checkDupId(m.getMId())){
            throw new CustomException(CommonEnum.JOIN_FAIL.getVal(), "/join");
        }
        memberRepository.saveMember(m);
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
        System.out.println("newPw : "+newPw);
        System.out.println("encodedPw : "+encoder.encode(newPw));
        m.setPw(encoder.encode(newPw));
        int updated = memberRepository.changePw(m);
        if (updated <= 0) {
            throw new CustomException(CommonEnum.FAIL_CHANGE_PW.getVal(), "/", true);
        }
    }

    @Override
    public Member changeMypage(int mNo, String data, String object) {
        return memberRepository.changeMypage(mNo, data, object)
                .orElseThrow(() -> new CustomException(CommonEnum.FAIL.getVal(),"/", true));
    }

    @Override
    public Member changeGrade(int mNo) {
        RoleEnum user = RoleEnum.USER;
        RoleEnum free = RoleEnum.FREELANCER;
        return memberRepository.changeGrade(mNo, user, free)
                .orElseThrow(() -> new CustomException(CommonEnum.FAIL.getVal(),"/", true));
    }

    @Override
    public Member updateFreelancer(Member m) {
        memberRepository.updateFreelancer(m);
        return memberRepository.findBymNo(m.getMNo())
                .orElseThrow(() -> new CustomException(CommonEnum.FAIL.getVal(),"/", true));
    }

    @Override
    @Transactional
    public void unregisterMember(int mNo) {
        tradeStatus(mNo);
        setDeleteStatusY(mNo);
        deleteMember(mNo);
    }

    public void deleteMember(int mNo){
        memberRepository.deleteMember(mNo);
    }

    public void setDeleteStatusY(Integer mNo) {
        serviceRepository.setDeleteStatusY(mNo);
    }

    public void tradeStatus(int mNo) {
        Optional.of(serviceTradeRepository.countBymNoAndtStatus(mNo, 1))
                .filter(count -> count == 0)
                .orElseThrow(() -> new CustomException("거래 중인 서비스가 있기 때문에 탈퇴할 수 없습니다", "/userMypage", true));
    }

}
