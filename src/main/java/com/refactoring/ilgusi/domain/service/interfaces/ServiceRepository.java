package com.refactoring.ilgusi.domain.service.interfaces;

import com.refactoring.ilgusi.domain.category.Category;
import com.refactoring.ilgusi.domain.member.Member;
import com.refactoring.ilgusi.domain.service.Service;
import com.refactoring.ilgusi.domain.service.ServiceFile;
import com.refactoring.ilgusi.domain.service.ServicePay;
import com.refactoring.ilgusi.domain.service.ServiceReview;
import com.refactoring.ilgusi.domain.service.dto.ReviewDto;
import com.refactoring.ilgusi.domain.service.dto.ServiceInsertDto;
import org.hibernate.annotations.Sort;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface ServiceRepository {

    Service insertService(Service s);

    List<Service> selectServiceList(int mNo, String order);

    List<ReviewDto> selectReviewList(String mId, int start, int end);


    int selectServiceNo();

    int insertServiceFile(int serviceNo, String filename, String filepath) ;

    List<Service> serviceList(String mId);

    int totalCount(String mId) ;

    // 마이페이지 - 서비스 후기 등록
    int serviceReviewInsert(ServiceReview sr);

    // 마이페이지에서 후기 등록하면 tStatus 바꿔줌(리뷰 작성완료로 : 3)
    int serviceReviewSuccess(int tNo);

    // 마이페이지 - 거래 후기 작성한거 확인
    ServiceReview serviceReviewView(ServiceReview data) ;

    // 거래 후기 수정
    int serviceReviewUpdate(ServiceReview review) ;

    // 거래 삭제
    int serviceReviewDelete(int rNo);

    // 거래 삭제했을때 tStatus 수정
    int serviceTradeStatusUpdate(int tNo);

    // 서비스 리스트 - 카테고리 불러오기
    ArrayList<Category> categoryList(int cNo) ;

    // 서비스 리스트 페이징
    ArrayList<Service> selectServiceList(HashMap<String, Object> map);

    // 서비스 토탈 카운트
    int serviceTotalCount(HashMap<String, Object> map) ;

    // 서비스 상세보기
    Service selectServiceView(int sNo);

    // 서비스 리뷰 불러오기
    ArrayList<ServiceReview> serviceViewReviewList(int sNo, int start, int end) ;

    // 리뷰 페이징을 위한 토탈카운트
    int totalReviewCount(int sNo);

    // 전문가 정보 불러오기
    Member selectMemberName(String memberId);

    // 다른 서비스 불러오기
    ArrayList<Service> userService(String memberId) ;

    // 서비스파일 리스트
    ArrayList<ServiceFile> fileList(int sNo) ;

    // review총 갯수 구하기
    List<ServiceReview> reviewListSize(String mId);

    float sRateAVG(String mId) ;

    // 결제 진행
    int insertServicePay(ServicePay pay) ;

    // tradeStatus 변경
    int updateTradeStatus(int tNo);

    // search service
    List<Service> searchService(int begin, int end, String keyword) ;

    // search serviceCount
    int selectServiceCount(String keyword);

    // 프리랜서마이페이지 서비스 삭제
    int deleteService(int sNo);



    // 리뷰 작성하면 서비스테이블 s_rate에 평점 넣어줌
    int serviceUpdateSRate(int sNo);

    // 프리랜서가 등록한 총 서비스 개수
    int selectFreeServiceCount(int mNo);

    void setDeleteStatusY(int mNo);

    void save(Service s);
}
