package com.refactoring.ilgusi.domain.service.interfaces;

import java.util.Optional;

public interface ServiceTradeRepository {
    // 메인페이지에 판매진행,구매진행중 정보 전달
    int selectBuyingCount(int mNo);
    int selectSellingCount(String mId);
    Integer countBymNoAndtStatus(int mNo, int tStatus);
}
