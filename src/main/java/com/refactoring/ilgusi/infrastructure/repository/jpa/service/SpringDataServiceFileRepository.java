package com.refactoring.ilgusi.infrastructure.repository.jpa.service;

import com.refactoring.ilgusi.domain.service.ServiceFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SpringDataServiceFileRepository extends JpaRepository<ServiceFile, Integer> {

    @Query("SELECT f FROM ServiceFile f WHERE f.service.serviceNo = :serviceNo ")
    List<ServiceFile> selectServiceFileList(int serviceNo);
}
