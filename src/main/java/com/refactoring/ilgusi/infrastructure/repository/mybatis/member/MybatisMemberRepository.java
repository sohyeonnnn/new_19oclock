package com.refactoring.ilgusi.infrastructure.repository.mybatis.member;

import com.refactoring.ilgusi.domain.member.Member;
import com.refactoring.ilgusi.domain.member.MemberRepository;
import com.refactoring.ilgusi.infrastructure.repository.jpa.member.SpringDataMemberRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

// 🔸 공통 인터페이스를 구현하고 내부에서 MyBatis를 사용
@Profile("mybatis")
//@Repository
public class MybatisMemberRepository /*implements MemberRepository*/ {

    private final MemberMapper mapper;

    public MybatisMemberRepository(MemberMapper mapper) {
        this.mapper = mapper;
    }

/*

    @Override
    public Member findById(Long id) {
        return null;
    }

    @Override
    public List<Member> findByName(String name) {
        return Collections.emptyList();
    }

    @Override
    public Member save(Member member) {
        return null;
    }*/
}