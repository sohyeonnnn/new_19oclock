package com.refactoring.ilgusi.infrastructure.repository.jpa.service;

import com.refactoring.ilgusi.domain.service.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface SpringDataServiceRepository extends JpaRepository<Service, Integer> {

    Optional<Service> findBymNo(int mNo);

    @Modifying
    @Query("UPDATE Service s SET s.deleteStatus = 'Y', s.sTitle = CONCAT('(삭제)', s.sTitle) WHERE s.mNo = :mNo")
    void setDeleteStatusY(@Param("mNo") int mNo);
}
