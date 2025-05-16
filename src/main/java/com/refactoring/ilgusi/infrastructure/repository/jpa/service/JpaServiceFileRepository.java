package com.refactoring.ilgusi.infrastructure.repository.jpa.service;

import com.refactoring.ilgusi.domain.service.ServiceFile;
import com.refactoring.ilgusi.domain.service.interfaces.ServiceFileRepository;

import java.util.List;

public class JpaServiceFileRepository implements ServiceFileRepository {
    private final SpringDataServiceFileRepository jpaRepository;

    public JpaServiceFileRepository(SpringDataServiceFileRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }


    @Override
    public ServiceFile insertServiceFile(ServiceFile file) {
        return jpaRepository.save(file);
    }

    @Override
    public List<ServiceFile> selectServiceFileList(int serviceNo) {
        return jpaRepository.selectServiceFileList(serviceNo);
    }
}
