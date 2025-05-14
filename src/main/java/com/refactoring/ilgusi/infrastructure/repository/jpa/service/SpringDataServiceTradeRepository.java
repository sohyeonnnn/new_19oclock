package com.refactoring.ilgusi.infrastructure.repository.jpa.service;

import com.refactoring.ilgusi.domain.service.ServiceTrade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SpringDataServiceTradeRepository extends JpaRepository<ServiceTrade, Integer> {

    @Query("SELECT COUNT(t) FROM ServiceTrade t WHERE t.memberNo = :memberNo AND t.tradeStatus = :tradeStatus")
    Integer countBymNoAndtStatus(@Param("memberNo") int memberNo, @Param("tradeStatus") int tradeStatus);

}
