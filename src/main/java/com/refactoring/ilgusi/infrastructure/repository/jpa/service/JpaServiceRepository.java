package com.refactoring.ilgusi.infrastructure.repository.jpa.service;

import com.refactoring.ilgusi.domain.category.Category;
import com.refactoring.ilgusi.domain.member.Member;
import com.refactoring.ilgusi.domain.service.ServiceItem;
import com.refactoring.ilgusi.domain.service.ServicePay;
import com.refactoring.ilgusi.domain.service.ServiceReview;
import com.refactoring.ilgusi.domain.service.dto.ServiceInfoDto;
import com.refactoring.ilgusi.domain.service.interfaces.ServiceRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class JpaServiceRepository implements ServiceRepository {
    private final SpringDataServiceRepository jpaRepository;

    public JpaServiceRepository(SpringDataServiceRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
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
            return jpaRepository.selectApprovedServiceList(memberNo);
        }else if(order.equals("rejected")){
            return jpaRepository.selectRejectedServiceList(memberNo);
        }
        return null;
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
    public int selectServiceNo() {
        return 0;
    }







    @Override
    public List<ServiceItem> serviceList(String mId) {
        return null;
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
    public ServiceItem selectServiceView(int sNo) {
        return null;
    }


    @Override
    public Member selectMemberName(String memberId) {
        return null;
    }

    @Override
    public ArrayList<ServiceItem> userService(String memberId) {
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
