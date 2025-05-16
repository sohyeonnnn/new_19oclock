package com.refactoring.ilgusi.domain.service.interfaces;


import com.refactoring.ilgusi.domain.service.ServiceItem;
import com.refactoring.ilgusi.domain.service.dto.ServiceInfoDto;
import com.refactoring.ilgusi.domain.service.dto.ServiceInsertDto;

import java.util.List;

public interface ServiceService {
    Integer selectFreelancerServiceCount(int memberNo);
    void insertService(ServiceInsertDto service);
    List<ServiceInfoDto> selectOrderedServiceList(int memberNo, String order);
    ServiceInfoDto selectServiceView(int serviceNo);
    void deleteService(int serviceNo);
}
