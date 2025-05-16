package com.refactoring.ilgusi.domain.service.interfaces;

import com.refactoring.ilgusi.domain.service.ServiceReview;
import com.refactoring.ilgusi.domain.service.dto.ReviewDto;

import java.util.ArrayList;
import java.util.List;

public interface ServiceReviewRepository {
    List<ReviewDto> selectReviewList(String memberId, int start, int end);



    // 마이페이지 - 서비스 후기 등록
    int serviceReviewInsert(ServiceReview sr);
    // 거래 후기 수정
    int serviceReviewUpdate(ServiceReview review) ;

    // 거래 삭제
    int serviceReviewDelete(int rNo);

    // 서비스 리뷰 불러오기
    ArrayList<ServiceReview> serviceViewReviewList(int sNo, int start, int end) ;

    // 리뷰 페이징을 위한 토탈카운트
    int totalReviewCount(int sNo);
    // review총 갯수 구하기
    List<ServiceReview> reviewListSize(String mId);



}
