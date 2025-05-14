package com.refactoring.ilgusi.infrastructure.repository.jpa.service;

import com.refactoring.ilgusi.domain.category.Category;
import com.refactoring.ilgusi.domain.member.Member;
import com.refactoring.ilgusi.domain.service.Service;
import com.refactoring.ilgusi.domain.service.ServiceFile;
import com.refactoring.ilgusi.domain.service.ServicePay;
import com.refactoring.ilgusi.domain.service.ServiceReview;
import com.refactoring.ilgusi.domain.service.dto.ReviewDto;
import com.refactoring.ilgusi.domain.service.interfaces.ServiceFileRepository;
import com.refactoring.ilgusi.domain.service.interfaces.ServiceRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

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
    public List<ServiceFile> fileList(int sNo) {
        return null;
    }
}
