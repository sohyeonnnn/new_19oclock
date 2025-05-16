package com.refactoring.ilgusi.infrastructure.repository.jpa.service;

import com.refactoring.ilgusi.domain.service.ServiceFile;
import com.refactoring.ilgusi.domain.service.dto.ServiceInfoDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SpringDataServiceFileRepository extends JpaRepository<ServiceFile, Integer> {

    @Query("SELECT f FROM ServiceFile f WHERE f.service.serviceNo = :serviceNo ")
    List<ServiceFile> selectServiceFileList(int serviceNo);
}
