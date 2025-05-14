package com.refactoring.ilgusi.domain.service.interfaces;

public interface ServiceTradeRepository {
    // 메인페이지에 판매진행,구매진행중 정보 전달
    int selectBuyingCount(int memberNo);
    int selectSellingCount(int memberNo);
    Integer countBymNoAndtStatus(int memberNo, int tradeStatus);
}
