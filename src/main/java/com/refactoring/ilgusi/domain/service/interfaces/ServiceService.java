package com.refactoring.ilgusi.domain.service.interfaces;


import com.refactoring.ilgusi.domain.service.Service;
import com.refactoring.ilgusi.domain.service.dto.ServiceInsertDto;

import java.util.List;

public interface ServiceService {
    Integer selectFreelancerServiceCount(int memberNo);
    void insertService(ServiceInsertDto service);
    List<Service> selectOrderedServiceList(int mNo, String order);
}
