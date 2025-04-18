package com.refactoring.ilgusi.infrastructure.repository.jpa.request;

import com.refactoring.ilgusi.domain.request.Request;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataRequestRepository extends JpaRepository<Request, Integer> {
}
