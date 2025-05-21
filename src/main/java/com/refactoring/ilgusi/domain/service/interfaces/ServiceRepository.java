package com.refactoring.ilgusi.domain.service.interfaces;

import com.refactoring.ilgusi.domain.category.Category;
import com.refactoring.ilgusi.domain.member.Member;
import com.refactoring.ilgusi.domain.notice.Notice;
import com.refactoring.ilgusi.domain.service.ServiceItem;
import com.refactoring.ilgusi.domain.service.ServicePay;
import com.refactoring.ilgusi.domain.service.ServiceReview;
import com.refactoring.ilgusi.domain.service.dto.ServiceInfoDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public interface ServiceRepository {

    int selectFreeServiceCount(int memberNo);
    void insertService(ServiceItem serviceItem);
    List<ServiceInfoDto> selectServiceList(int memberNo, String order);
    Optional<ServiceInfoDto> selectServiceView(int serviceNo);
    void setMemberServiceDeleteStatusY(int memberNo);
    void setServiceDeleteStatusY(int memberNo);

    Page<ServiceInfoDto> selectCategoryServiceList(Pageable pageable, int mainCategoryCd, int categoryCd, String order, String keyword);


    int selectServiceNo();



    // 마이페이지에서 후기 등록하면 tStatus 바꿔줌(리뷰 작성완료로 : 3)
    int serviceReviewSuccess(int tNo);

    // 마이페이지 - 거래 후기 작성한거 확인
    ServiceReview serviceReviewView(ServiceReview data) ;



    // 거래 삭제했을때 tStatus 수정
    int serviceTradeStatusUpdate(int tNo);

    // 서비스 리스트 - 카테고리 불러오기
    ArrayList<Category> categoryList(int cNo) ;

    // 서비스 리스트 페이징
    ArrayList<ServiceItem> selectServiceList(HashMap<String, Object> map);

    // 서비스 토탈 카운트
    int serviceTotalCount(HashMap<String, Object> map) ;

    // 서비스 상세보기
    ServiceItem selectServiceView2(int sNo);


    // 전문가 정보 불러오기
    Member selectMemberName(String memberId);


    float sRateAVG(String mId) ;

    // 결제 진행
    int insertServicePay(ServicePay pay) ;

    // tradeStatus 변경
    int updateTradeStatus(int tNo);

    // search service
    List<ServiceItem> searchService(int begin, int end, String keyword) ;

    // search serviceCount
    int selectServiceCount(String keyword);

    // 프리랜서마이페이지 서비스 삭제
    int deleteService(int serviceNo);



    // 리뷰 작성하면 서비스테이블 s_rate에 평점 넣어줌
    int serviceUpdateSRate(int serviceNo);

}
