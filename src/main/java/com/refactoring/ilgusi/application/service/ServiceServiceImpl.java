package com.refactoring.ilgusi.application.service;

import com.refactoring.ilgusi.domain.member.interfaces.MemberRepository;
import com.refactoring.ilgusi.domain.service.Join;
import com.refactoring.ilgusi.domain.service.interfaces.ServiceRepository;
import com.refactoring.ilgusi.domain.service.interfaces.ServiceService;
import com.refactoring.ilgusi.domain.service.interfaces.ServiceTradeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
public class ServiceServiceImpl implements ServiceService {
    private final MemberRepository memberRepository;
    private final ServiceTradeRepository serviceTradeRepository;
    private final ServiceRepository serviceRepository;

    public int selectFreeServiceCount(int mNo){
        return serviceRepository.selectFreeServiceCount(mNo);
    }

    public void insertService(Join join){
        return serviceRepository.save(join);
    }


}
