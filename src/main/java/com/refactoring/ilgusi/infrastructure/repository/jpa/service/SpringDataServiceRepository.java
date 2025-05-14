package com.refactoring.ilgusi.infrastructure.repository.jpa.service;

import com.refactoring.ilgusi.domain.service.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SpringDataServiceRepository extends JpaRepository<Service, Integer> {


    @Modifying
    @Query("UPDATE Service s SET s.deleteStatus = 'Y', s.serviceTitle = CONCAT('(삭제)', s.serviceTitle) WHERE s.memberNo = :memberNo AND s.deleteStatus = 'N'")
    void setDeleteStatusY(@Param("memberNo") int memberNo);

    @Query("SELECT COUNT(s) FROM Service s WHERE s.memberNo = :memberNo AND s.deleteStatus = 'N' and s.adminApproval = 'Y' ")
    Integer countServiceBymNo(@Param("memberNo") int memberNo);

    @Query("SELECT s FROM Service s Where s.memberNo= :memberNo AND s.adminApproval = 'Y' AND s.deleteStatus = 'N'")
     Optional<List<Service>> selectApprovedServiceList(@Param("memberNo") int memberNo);

    @Query("SELECT s FROM Service s Where s.memberNo= :memberNo AND s.adminApproval = 'N' AND s.deleteStatus = 'N'")
    Optional<List<Service>> selectRejectedServiceList(@Param("memberNo") int memberNo);

}
