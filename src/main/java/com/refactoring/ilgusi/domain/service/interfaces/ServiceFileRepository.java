package com.refactoring.ilgusi.domain.service.interfaces;

import com.refactoring.ilgusi.domain.category.Category;
import com.refactoring.ilgusi.domain.member.Member;
import com.refactoring.ilgusi.domain.service.Service;
import com.refactoring.ilgusi.domain.service.ServiceFile;
import com.refactoring.ilgusi.domain.service.ServicePay;
import com.refactoring.ilgusi.domain.service.ServiceReview;
import com.refactoring.ilgusi.domain.service.dto.ReviewDto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public interface ServiceFileRepository {

    ServiceFile insertServiceFile(ServiceFile file);

    // 서비스파일 리스트
    List<ServiceFile> fileList(int sNo) ;


}
