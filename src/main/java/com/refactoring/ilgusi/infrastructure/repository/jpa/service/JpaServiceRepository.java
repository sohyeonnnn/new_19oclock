package com.refactoring.ilgusi.infrastructure.repository.jpa.service;

import com.refactoring.ilgusi.domain.category.Category;
import com.refactoring.ilgusi.domain.member.Member;
import com.refactoring.ilgusi.domain.service.ServiceItem;
import com.refactoring.ilgusi.domain.service.ServicePay;
import com.refactoring.ilgusi.domain.service.ServiceReview;
import com.refactoring.ilgusi.domain.service.dto.ServiceInfoDto;
import com.refactoring.ilgusi.domain.service.interfaces.ServiceRepository;
import com.refactoring.ilgusi.exception.CustomException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class JpaServiceRepository implements ServiceRepository {
    private final SpringDataServiceRepository jpaRepository;

    public JpaServiceRepository(SpringDataServiceRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Optional<ServiceItem> findById(int serviceNo) {
        return jpaRepository.findById(serviceNo);
    }

    @Override
    public int selectFreeServiceCount(int mNo) {
        return jpaRepository.countServiceBymNo(mNo);
    }

    @Override
    public void insertService(ServiceItem serviceItem) {
        jpaRepository.save(serviceItem);
    }

    @Override
    public List<ServiceInfoDto> selectServiceList(int memberNo, String order) {
        if(order.equals("approved")){
            return jpaRepository.selectSelectedServiceList(memberNo, "Y", "N");
        }else if(order.equals("rejected")){
            return jpaRepository.selectSelectedServiceList(memberNo, "N", "N");
        }
        return null;
    }

    @Override
    public Optional<ServiceInfoDto> selectServiceView(int serviceNo) {
        return Optional.ofNullable(jpaRepository.selectServiceView(serviceNo, "Y", "N"))
                .orElseThrow(()-> new CustomException("없음"));

    }

    @Override
    public void setMemberServiceDeleteStatusY(int memberNo) {
        jpaRepository.setMemberServiceDeleteStatusY(memberNo);
    }

    @Override
    public void setServiceDeleteStatusY(int serviceNo) {
        jpaRepository.setServiceDeleteStatusY(serviceNo);
    }

    @Override
    public Page<ServiceInfoDto> selectCategoryServiceList(Pageable pageable, int mainCategoryCd, int categoryCd, String order, String keyword) {
        return jpaRepository.findByKeywordWithRangeOrderBy(pageable, mainCategoryCd, categoryCd, keyword);
    }


    @Override
    public int selectServiceNo() {
        return 0;
    }









    @Override
    public int serviceReviewSuccess(int tNo) {
        return 0;
    }

    @Override
    public ServiceReview serviceReviewView(ServiceReview data) {
        return null;
    }


    @Override
    public int serviceTradeStatusUpdate(int tNo) {
        return 0;
    }

    @Override
    public ArrayList<Category> categoryList(int cNo) {
        return null;
    }

    @Override
    public ArrayList<ServiceItem> selectServiceList(HashMap<String, Object> map) {
        return null;
    }

    @Override
    public int serviceTotalCount(HashMap<String, Object> map) {
        return 0;
    }

    @Override
    public ServiceItem selectServiceView2(int sNo) {
        return null;
    }

    @Override
    public float sRateAVG(String mId) {
        return 0;
    }

    @Override
    public int insertServicePay(ServicePay pay) {
        return 0;
    }

    @Override
    public int updateTradeStatus(int tNo) {
        return 0;
    }

    @Override
    public List<ServiceItem> searchService(int begin, int end, String keyword) {
        return null;
    }

    @Override
    public int selectServiceCount(String keyword) {
        return 0;
    }

    @Override
    public int deleteService(int sNo) {
        return 0;
    }

    @Override
    public int serviceUpdateSRate(int sNo) {
        return 0;
    }

}
