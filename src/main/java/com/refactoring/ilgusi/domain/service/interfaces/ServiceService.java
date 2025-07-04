package com.refactoring.ilgusi.domain.service.interfaces;


import com.refactoring.ilgusi.domain.service.ServiceFile;
import com.refactoring.ilgusi.domain.service.dto.ServiceInfoDto;
import com.refactoring.ilgusi.domain.service.dto.ServiceInsertDto;
import com.refactoring.ilgusi.domain.service.dto.ServicePageDto;

import java.util.List;

public interface ServiceService {
    int selectFreelancerServiceCount(int memberNo);
    void insertService(ServiceInsertDto dto, List<ServiceFile> fileList);
    List<ServiceInfoDto> selectOrderedServiceList(int memberNo, String order);
    ServiceInfoDto selectServiceView(int serviceNo);
    void deleteService(int serviceNo);

    List<ServiceFile> selectServiceFileList(int serviceNo);

    List<ServiceInfoDto> selectCategoryServiceList(int reqPage, String keyword, int categoryCd);

    ServicePageDto selectServiceListApi(int mainCategoryCd, int categoryCd, int reqPage, String order, String keyword);

}
