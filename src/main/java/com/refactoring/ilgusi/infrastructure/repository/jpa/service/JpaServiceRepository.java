package com.refactoring.ilgusi.infrastructure.repository.jpa.service;

import com.refactoring.ilgusi.domain.category.Category;
import com.refactoring.ilgusi.domain.member.Member;
import com.refactoring.ilgusi.domain.service.*;
import com.refactoring.ilgusi.domain.service.interfaces.ServiceRepository;

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
    public Join selectOneMember(String mId) {
        return null;
    }

    @Override
    public int insertService(Join join) {
        return 0;
    }

    @Override
    public int updateFreelancer(Member m) {
        return 0;
    }

    @Override
    public Member selectOneMember(int MNO) {
        return null;
    }

    @Override
    public List<ReviewJoin> selectReviewList(String mId, int start, int end) {
        return List.of();
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
        return List.of();
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
        return List.of();
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
        return List.of();
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
    public ArrayList<Service> selectServiceList(String mId, String order) {
        return null;
    }

    @Override
    public int serviceUpdateSRate(int sNo) {
        return 0;
    }

    @Override
    public int selectFreeServiceCount(String mId) {
        return 0;
    }

    @Override
    public Optional<Service> tradeStatus(int mNo){
        return jpaRepository.findBymNo(mNo);
    }

}
