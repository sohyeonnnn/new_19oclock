package com.refactoring.ilgusi.infrastructure.repository.jpa.service;

import com.refactoring.ilgusi.domain.member.Member;
import com.refactoring.ilgusi.domain.service.Service;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataServiceRepository extends JpaRepository<Service, Integer> {

    Optional<Service> findBymNo(int mNo);
}
