package com.refactoring.ilgusi.domain.service.interfaces;

import com.refactoring.ilgusi.domain.service.ServiceFile;

import java.util.List;

public interface ServiceFileRepository {

    ServiceFile insertServiceFile(ServiceFile file);

    // 서비스파일 리스트
    List<ServiceFile> selectServiceFileList(int serviceNo) ;


}
