package com.refactoring.ilgusi.domain.service.interfaces;


import com.refactoring.ilgusi.domain.service.Service;
import com.refactoring.ilgusi.domain.service.dto.ServiceInsertDto;

import java.util.List;

public interface ServiceService {
    int selectFreelancerServiceCount(int mNo);
    void insertService(ServiceInsertDto join);
    List<Service> selectOrderedServiceList(int mNo, String order);
}
