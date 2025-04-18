package com.refactoring.ilgusi.infrastructure.repository.jpa.service;

import com.refactoring.ilgusi.domain.service.ServiceTrade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataServiceTradeRepository extends JpaRepository<ServiceTrade, Integer> {
}
