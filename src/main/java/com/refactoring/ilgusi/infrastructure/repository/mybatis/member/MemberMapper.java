package com.refactoring.ilgusi.infrastructure.repository.mybatis.member;

import com.refactoring.ilgusi.domain.member.Member;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

// 🔸 실제 SQL 매핑을 수행할 MyBatis 인터페이스
@Mapper
public interface MemberMapper {
    /*Member findById(Long id);
    List<Member> findByName(String name);
    void insert(Member member);*/
}