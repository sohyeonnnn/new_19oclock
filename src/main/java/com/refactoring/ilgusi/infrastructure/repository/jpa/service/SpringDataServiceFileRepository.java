package com.refactoring.ilgusi.infrastructure.repository.jpa.service;

import com.refactoring.ilgusi.domain.service.ServiceFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataServiceFileRepository extends JpaRepository<ServiceFile, Integer> {

}
