package com.refactoring.ilgusi.application.member;


import com.refactoring.ilgusi.domain.member.Member;
import com.refactoring.ilgusi.domain.member.dto.FreelancerIntroduceDto;
import com.refactoring.ilgusi.domain.member.interfaces.FreelancerService;
import com.refactoring.ilgusi.domain.member.interfaces.MemberRepository;
import com.refactoring.ilgusi.domain.service.interfaces.ServiceRepository;
import com.refactoring.ilgusi.domain.service.interfaces.ServiceTradeRepository;
import com.refactoring.ilgusi.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
@RequiredArgsConstructor
@Service
public class FreelancerServiceImpl implements FreelancerService {
    private final MemberRepository memberRepository;
    private final ServiceTradeRepository serviceTradeRepository;
    private final ServiceRepository serviceRepository;


    @Override
    public FreelancerIntroduceDto selectFreelancerIntroduce(int memberNo) {
        Member member = memberRepository.selectFreelancerIntroduce(memberNo)
                .orElseThrow(() -> new CustomException("소개 없음", "/"));
        return FreelancerIntroduceDto.from(member);
    }


}
