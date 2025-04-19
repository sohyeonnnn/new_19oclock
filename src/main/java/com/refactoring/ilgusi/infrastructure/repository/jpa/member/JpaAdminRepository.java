package com.refactoring.ilgusi.infrastructure.repository.jpa.member;


import com.refactoring.ilgusi.domain.member.interfaces.AdminRepository;

// 🔸 공통 인터페이스를 구현하고 내부에서 Spring Data JPA를 사용

//@Repository
public class JpaAdminRepository implements AdminRepository {

    private final SpringDataAdminRepository jpaRepository;

    public JpaAdminRepository(SpringDataAdminRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

}