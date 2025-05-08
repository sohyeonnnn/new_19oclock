package com.refactoring.ilgusi.application.service;

import com.refactoring.ilgusi.domain.member.interfaces.MemberRepository;
import com.refactoring.ilgusi.domain.service.dto.ServiceInsertDto;
import com.refactoring.ilgusi.domain.service.interfaces.ServiceRepository;
import com.refactoring.ilgusi.domain.service.interfaces.ServiceService;
import com.refactoring.ilgusi.domain.service.interfaces.ServiceTradeRepository;
import com.refactoring.ilgusi.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@RequiredArgsConstructor
@Service
public class ServiceServiceImpl implements ServiceService {
    private final MemberRepository memberRepository;
    private final ServiceTradeRepository serviceTradeRepository;
    private final ServiceRepository serviceRepository;

    public Integer selectFreelancerServiceCount(int mNo){
        return serviceRepository.selectFreeServiceCount(mNo);
    }

    public void insertService(ServiceInsertDto s){
        serviceRepository.insertService(s.toEntity());
    }

    @Override
    public List<com.refactoring.ilgusi.domain.service.Service> selectOrderedServiceList(int mNo, String order) {
        return serviceRepository.selectServiceList(mNo, order)
                .orElseThrow(() -> new CustomException("서비스 없음", "freelancer/freelancerMypage"));
    }


}
