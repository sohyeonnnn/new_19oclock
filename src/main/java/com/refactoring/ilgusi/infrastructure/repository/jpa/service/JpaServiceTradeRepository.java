package com.refactoring.ilgusi.infrastructure.repository.jpa.service;

import com.refactoring.ilgusi.domain.service.interfaces.ServiceTradeRepository;


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
    public int selectSellingCount(int mNo) {
        return 0;
    }

    @Override
    public Integer countBymNoAndtStatus(int mNo, int tStatus) {
        return jpaRepository.countBymNoAndtStatus(mNo, tStatus);
    }
}
