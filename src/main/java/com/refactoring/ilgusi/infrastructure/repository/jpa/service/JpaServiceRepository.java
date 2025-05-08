package com.refactoring.ilgusi.infrastructure.repository.jpa.service;

import com.refactoring.ilgusi.domain.category.Category;
import com.refactoring.ilgusi.domain.member.Member;
import com.refactoring.ilgusi.domain.service.Service;
import com.refactoring.ilgusi.domain.service.ServiceFile;
import com.refactoring.ilgusi.domain.service.ServicePay;
import com.refactoring.ilgusi.domain.service.ServiceReview;
import com.refactoring.ilgusi.domain.service.dto.ReviewDto;
import com.refactoring.ilgusi.domain.service.dto.ServiceInsertDto;
import com.refactoring.ilgusi.domain.service.interfaces.ServiceRepository;
import com.refactoring.ilgusi.exception.CustomException;

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
    public Service insertService(Service s) {
        return jpaRepository.save(s);
    }

    @Override
    public Optional<List<Service>> selectServiceList(int mNo, String order) {
        System.out.println("order : "+order);
        if(order.equals("approved")){
            return jpaRepository.selectApprovedServiceList(mNo);
        }else if(order.equals("rejected")){
            return jpaRepository.selectRejectedServiceList(mNo);
        }
        return null;
    }

    @Override
    public List<ReviewDto> selectReviewList(String mId, int start, int end) {
        return null;
    }

    @Override
    public int selectServiceNo() {
        return 0;
    }

    @Override
    public int insertServiceFile(int serviceNo, String filename, String filepath) {
        return 0;
    }

    @Override
    public List<Service> serviceList(String mId) {
        return null;
    }

    @Override
    public int totalCount(String mId) {
        return 0;
    }

    @Override
    public int serviceReviewInsert(ServiceReview sr) {
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
    public int serviceReviewUpdate(ServiceReview review) {
        return 0;
    }

    @Override
    public int serviceReviewDelete(int rNo) {
        return 0;
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
    public ArrayList<Service> selectServiceList(HashMap<String, Object> map) {
        return null;
    }

    @Override
    public int serviceTotalCount(HashMap<String, Object> map) {
        return 0;
    }

    @Override
    public Service selectServiceView(int sNo) {
        return null;
    }

    @Override
    public ArrayList<ServiceReview> serviceViewReviewList(int sNo, int start, int end) {
        return null;
    }

    @Override
    public int totalReviewCount(int sNo) {
        return 0;
    }

    @Override
    public Member selectMemberName(String memberId) {
        return null;
    }

    @Override
    public ArrayList<Service> userService(String memberId) {
        return null;
    }

    @Override
    public ArrayList<ServiceFile> fileList(int sNo) {
        return null;
    }

    @Override
    public List<ServiceReview> reviewListSize(String mId) {
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
    public List<Service> searchService(int begin, int end, String keyword) {
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

    @Override
    public Integer selectFreeServiceCount(int mNo) {
        return jpaRepository.countServiceBymNo(mNo);
    }

    @Override
    public void setDeleteStatusY(int mNo) {

    }

    @Override
    public void save(Service s) {
        jpaRepository.save(s);
    }


}
