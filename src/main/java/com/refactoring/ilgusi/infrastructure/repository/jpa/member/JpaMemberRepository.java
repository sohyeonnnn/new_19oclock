package com.refactoring.ilgusi.infrastructure.repository.jpa.member;

import com.refactoring.ilgusi.domain.member.Member;
import com.refactoring.ilgusi.domain.member.MemberRepository;

import java.util.Optional;

// 🔸 공통 인터페이스를 구현하고 내부에서 Spring Data JPA를 사용

//@Repository
public class JpaMemberRepository  implements MemberRepository {

    private final SpringDataMemberRepository jpaRepository;

    public JpaMemberRepository(SpringDataMemberRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }




}