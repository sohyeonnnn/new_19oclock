package com.refactoring.ilgusi.infrastructure.repository.jpa.member;

import com.refactoring.ilgusi.domain.chat.ChatContent;
import com.refactoring.ilgusi.domain.member.AdminRepository;
import com.refactoring.ilgusi.domain.member.Member;
import com.refactoring.ilgusi.domain.member.MemberRepository;
import com.refactoring.ilgusi.domain.member.MemberViewData;
import com.refactoring.ilgusi.domain.question.Question;
import com.refactoring.ilgusi.domain.service.Service;
import com.refactoring.ilgusi.domain.service.ServiceInfo;
import com.refactoring.ilgusi.domain.service.ServiceViewData;
import com.refactoring.ilgusi.domain.service.TradeHistory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

// 🔸 공통 인터페이스를 구현하고 내부에서 Spring Data JPA를 사용

//@Repository
public class JpaAdminRepository implements AdminRepository {

    private final SpringDataAdminRepository jpaRepository;

    public JpaAdminRepository(SpringDataAdminRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

}