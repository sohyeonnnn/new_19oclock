package com.refactoring.ilgusi.application.service;

import com.refactoring.ilgusi.domain.member.Member;
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

    public int selectFreelancerServiceCount(int memberNo) {
        return serviceRepository.selectFreeServiceCount(memberNo);
    }

    @Override
    public void insertService(ServiceInsertDto dto, List<ServiceFile> fileList) {

        Member member = memberRepository.findByMemberNo(dto.getMemberNo())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));
        ServiceItem serviceItem = dto.toEntity();
        serviceItem.setMember(member);
        member.getServiceList().add(serviceItem);

        serviceRepository.insertService(serviceItem);

        // 파일 추가
        if (fileList != null) {
            for (ServiceFile file : fileList) {
                ServiceFile serviceFile = new ServiceFile();
                serviceFile.setFilename(file.getFilename());
                serviceFile.setFilepath(file.getFilepath());
                serviceFile.setService(serviceItem);
                serviceFileRepository.insertServiceFile(serviceFile);
            }
        }
    }

    @Override
    public List<ServiceInfoDto> selectOrderedServiceList(int memberNo, String order) {
        return Optional.ofNullable(serviceRepository.selectServiceList(memberNo, order))
                .orElseThrow(() -> new CustomException("서비스 없음", "redirect:/freelancerServiceList?order=rejected"));
    }

    @Override
    public ServiceInfoDto selectServiceView(int serviceNo) {
        return serviceRepository.selectServiceView(serviceNo).orElseThrow(()-> new CustomException("NULL"));
    }

    @Override
    public void deleteService(int serviceNo){
        serviceRepository.setServiceDeleteStatusY(serviceNo);
    }

    @Override
    public List<ServiceFile> selectServiceFileList(int serviceNo){
        return Optional.ofNullable(serviceFileRepository.selectServiceFileList(serviceNo))
                .orElseThrow(() -> new CustomException("서비스파일 없음", "redirect:/freelancerServiceList?order=rejected"));
    }

    @Override
    public List<ServiceInfoDto> selectCategoryServiceList(int reqPage, String keyword, int categoryCd) {

        int limit = 12;
        int offset = (reqPage - 1) * limit;

       /* List<ServiceItem> serviceList = serviceRepository.selectCategoryServiceList(offset, limit, keyword, categoryCd);
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println(serviceList.toString());
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");*/

        return null;
    }


}
