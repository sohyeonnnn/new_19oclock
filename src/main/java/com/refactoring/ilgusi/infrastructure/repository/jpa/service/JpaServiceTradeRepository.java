package com.refactoring.ilgusi.infrastructure.repository.jpa.service;

import com.refactoring.ilgusi.domain.service.Service;
import com.refactoring.ilgusi.domain.service.ServiceTrade;
import com.refactoring.ilgusi.domain.service.ServiceTradeRepository;

import java.util.ArrayList;


public class JpaServiceTradeRepository implements ServiceTradeRepository {
    private final SpringDataServiceTradeRepository jpaRepository;

    public JpaServiceTradeRepository(SpringDataServiceTradeRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

}
