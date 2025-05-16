package com.refactoring.ilgusi.application.service;

import com.refactoring.ilgusi.domain.member.interfaces.MemberRepository;
import com.refactoring.ilgusi.domain.service.ServiceFile;
import com.refactoring.ilgusi.domain.service.ServiceItem;
import com.refactoring.ilgusi.domain.service.dto.ServiceInfoDto;
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
import java.util.Optional;

@Transactional
@RequiredArgsConstructor
@Service
public class ServiceServiceImpl implements ServiceService {
    private final MemberRepository memberRepository;
    private final ServiceTradeRepository serviceTradeRepository;
    private final ServiceRepository serviceRepository;
    private final ServiceFileRepository serviceFileRepository;

    public Integer selectFreelancerServiceCount(int memberNo) {
        return serviceRepository.selectFreeServiceCount(memberNo);
    }

    @Override
    public void insertService(ServiceInsertDto dto) {
        ServiceItem serviceItem = dto.toEntity();

        // 파일 추가
        if (dto.getFileList() != null) {
            for (ServiceFile file : dto.getFileList()) {
                ServiceFile serviceFile = new ServiceFile();
                serviceFile.setFilename(file.getFilename());
                serviceFile.setFilepath(file.getFilepath());
                serviceItem.addFile(serviceFile);
            }
        }
        serviceRepository.insertService(serviceItem);
    }

    @Override
    public List<ServiceInfoDto> selectOrderedServiceList(int memberNo, String order) {
        return Optional.ofNullable(serviceRepository.selectServiceList(memberNo, order))
                .orElseThrow(() -> new CustomException("서비스 없음", "freelancer/freelancerMypage"));
    }

    @Override
    public ServiceInfoDto selectServiceView(int serviceNo) {
        return serviceRepository.selectServiceView(serviceNo).orElseThrow(()-> new CustomException("NULL"));
    }

    @Override
    public void deleteService(int serviceNo){
        serviceRepository.setServiceDeleteStatusY(serviceNo);
    }


}
