package com.refactoring.ilgusi.application.service;

import com.refactoring.ilgusi.domain.member.interfaces.MemberRepository;
import com.refactoring.ilgusi.domain.service.ServiceFile;
import com.refactoring.ilgusi.domain.service.dto.ServiceInsertDto;
import com.refactoring.ilgusi.domain.service.interfaces.ServiceFileRepository;
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
    private final ServiceFileRepository serviceFileRepository;

    public Integer selectFreelancerServiceCount(int memberNo){
        return serviceRepository.selectFreeServiceCount(memberNo);
    }

    @Override
    public void insertService(ServiceInsertDto dto) {
        serviceRepository.insertService(dto.toEntity());
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!    "+dto.toString());
        List<ServiceFile> serviceList = dto.getFileList();
        for(ServiceFile file : serviceList){
            System.out.println("************************   " + file.toString());
            serviceFileRepository.insertServiceFile(file);
        }

    }

    @Override
    public List<com.refactoring.ilgusi.domain.service.Service> selectOrderedServiceList(int mNo, String order) {
        return serviceRepository.selectServiceList(mNo, order)
                .orElseThrow(() -> new CustomException("서비스 없음", "freelancer/freelancerMypage"));
    }


}
