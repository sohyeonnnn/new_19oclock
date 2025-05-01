package com.refactoring.ilgusi.application.member;


import com.refactoring.ilgusi.domain.member.interfaces.FreelancerService;
import com.refactoring.ilgusi.domain.member.interfaces.MemberRepository;
import com.refactoring.ilgusi.domain.service.interfaces.ServiceRepository;
import com.refactoring.ilgusi.domain.service.interfaces.ServiceTradeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
public class FreelancerServiceImpl implements FreelancerService {
    private final MemberRepository memberRepository;
    private final ServiceTradeRepository serviceTradeRepository;
    private final ServiceRepository serviceRepository;



}
