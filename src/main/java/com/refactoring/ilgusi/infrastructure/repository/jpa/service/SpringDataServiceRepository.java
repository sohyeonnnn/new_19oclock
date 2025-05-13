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
    @Query("UPDATE Service s SET s.deleteStatus = 'Y', s.sTitle = CONCAT('(삭제)', s.sTitle) WHERE s.mNo = :mNo AND s.deleteStatus = 'N'")
    void setDeleteStatusY(@Param("mNo") int mNo);

    @Query("SELECT COUNT(s) FROM Service s WHERE s.mNo = :memberNo AND s.deleteStatus = 'N' and s.adminApproval = 'Y' ")
    Integer countServiceBymNo(@Param("memberNo") int memberNo);

    @Query("SELECT s FROM Service s Where s.mNo= :mNo AND s.adminApproval = 'Y' AND s.deleteStatus = 'N'")
     Optional<List<Service>> selectApprovedServiceList(@Param("mNo") int mNo);

    @Query("SELECT s FROM Service s Where s.mNo= :mNo AND s.adminApproval = 'N' AND s.deleteStatus = 'N'")
    Optional<List<Service>> selectRejectedServiceList(@Param("mNo") int mNo);

}
