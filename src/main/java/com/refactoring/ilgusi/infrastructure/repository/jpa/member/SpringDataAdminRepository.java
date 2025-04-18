package com.refactoring.ilgusi.infrastructure.repository.jpa.member;

import com.refactoring.ilgusi.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataAdminRepository extends JpaRepository<Member, Integer>{

}
