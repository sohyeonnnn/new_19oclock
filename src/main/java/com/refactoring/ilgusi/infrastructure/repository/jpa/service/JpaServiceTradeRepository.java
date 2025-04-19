package com.refactoring.ilgusi.infrastructure.repository.jpa.service;

import com.refactoring.ilgusi.domain.service.ServiceTradeRepository;




public class JpaServiceTradeRepository implements ServiceTradeRepository {
    private final SpringDataServiceTradeRepository jpaRepository;

    public JpaServiceTradeRepository(SpringDataServiceTradeRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public int selectBuyingCount(int mNo) {
        return 0;
    }

    @Override
    public int selectSellingCount(String mId) {
        return 0;
    }
}
