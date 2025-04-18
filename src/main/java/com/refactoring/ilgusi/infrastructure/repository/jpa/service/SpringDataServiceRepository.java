package com.refactoring.ilgusi.infrastructure.repository.jpa.service;

import com.refactoring.ilgusi.domain.service.Service;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataServiceRepository extends JpaRepository<Service, Integer> {
}
