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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class JpaServiceRepository implements ServiceRepository {
    private final SpringDataServiceRepository jpaRepository;

    public JpaServiceRepository(SpringDataServiceRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public ServiceInsertDto selectOneMember(String mId) {
        return null;
    }

    @Override
    public Service insertService(ServiceInsertDto join) {
        Service service = join.toEntity();
        return jpaRepository.save(service);
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
    public ArrayList<Service> selectServiceList(String mId, String order) {
        return null;
    }

    @Override
    public int serviceUpdateSRate(int sNo) {
        return 0;
    }

    @Override
    public int selectFreeServiceCount(int mNo) {
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
