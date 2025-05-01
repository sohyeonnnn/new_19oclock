package com.refactoring.ilgusi.infrastructure.repository.jpa.request;

import com.refactoring.ilgusi.domain.request.interfaces.RequestRepository;

public class JpaRequestRepository implements RequestRepository {
    private final SpringDataRequestRepository jpaRepository;

    public JpaRequestRepository(SpringDataRequestRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }


}
