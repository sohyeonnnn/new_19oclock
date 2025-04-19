package com.refactoring.ilgusi.infrastructure.repository.mybatis.member;

import org.springframework.context.annotation.Profile;

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