package com.refactoring.ilgusi.infrastructure.repository.jpa.service;

import com.refactoring.ilgusi.domain.notice.Notice;
import com.refactoring.ilgusi.domain.service.ServiceItem;
import com.refactoring.ilgusi.domain.service.dto.ServiceInfoDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SpringDataServiceRepository extends JpaRepository<ServiceItem, Integer> {


    @Modifying
    @Query("UPDATE ServiceItem s SET s.deleteStatus = 'Y', s.serviceTitle = CONCAT('(삭제)', s.serviceTitle) WHERE s.member.memberNo = :memberNo AND s.deleteStatus = 'N'")
    void setMemberServiceDeleteStatusY(@Param("memberNo") int memberNo);

    @Modifying
    @Query("UPDATE ServiceItem s SET s.deleteStatus = 'Y', s.serviceTitle = CONCAT('(삭제)', s.serviceTitle) WHERE s.serviceNo = :serviceNo AND s.deleteStatus = 'N'")
    void setServiceDeleteStatusY(@Param("serviceNo") int serviceNo);

    @Query("SELECT COUNT(s) FROM ServiceItem s WHERE s.member.memberNo = :memberNo AND s.deleteStatus = 'N' and s.adminApproval = 'Y' ")
    Integer countServiceBymNo(@Param("memberNo") int memberNo);

    @Query(
            "SELECT new com.refactoring.ilgusi.domain.service.dto.ServiceInfoDto(" +
                    "s.serviceNo, s.serviceTitle, s.serviceContent, s.servicePrice, s.serviceArea, " +
                    "s.serviceImg, s.serviceRate, s.mainCategory, s.subCategory, s.workingDate, " +
                    "s.workingCount, s.writeDate, s.deleteStatus, s.adminApproval, " +
                    "s.member.memberNo, m.memberId, m.brandName, m.introduce, mainCat.categoryName, subCat.categoryName) " +
                    "FROM ServiceItem s " +
                    "LEFT JOIN Member m ON s.member.memberNo = m.memberNo " +
                    "LEFT JOIN Category mainCat ON s.mainCategory = mainCat.categoryCd " +
                    "LEFT JOIN Category subCat ON s.subCategory = subCat.categoryCd " +
                    "WHERE s.member.memberNo = :memberNo " +
                    "AND s.adminApproval = :isApproved " +
                    "AND s.deleteStatus = :isDeleted"
    )
    List<ServiceInfoDto> selectSelectedServiceList(@Param("memberNo") int memberNo, @Param("isApproved") String isApproved, @Param("isDeleted") String isDeleted);

    @Query(
            "SELECT new com.refactoring.ilgusi.domain.service.dto.ServiceInfoDto(" +
                    "s.serviceNo, s.serviceTitle, s.serviceContent, s.servicePrice, s.serviceArea, " +
                    "s.serviceImg, s.serviceRate, s.mainCategory, s.subCategory, s.workingDate, " +
                    "s.workingCount, s.writeDate, s.deleteStatus, s.adminApproval, " +
                    "s.member.memberNo, m.memberId, m.brandName, m.introduce, mainCat.categoryName, subCat.categoryName) " +
                    "FROM ServiceItem s " +
                    "LEFT JOIN Member m ON s.member.memberNo = m.memberNo " +
                    "LEFT JOIN Category mainCat ON s.mainCategory = mainCat.categoryCd " +
                    "LEFT JOIN Category subCat ON s.subCategory = subCat.categoryCd " +
                    "WHERE s.serviceNo = :serviceNo " +
                    "AND s.adminApproval = :isApproved " +
                    "AND s.deleteStatus = :isDeleted "
    )
    Optional<ServiceInfoDto> selectServiceView(@Param("serviceNo") int serviceNo, @Param("isApproved") String isApproved, @Param("isDeleted") String isDeleted);

    @Query(value = "SELECT * FROM serviceItem WHERE subCategory = :categoryCd AND (:keyword IS NULL OR service_title LIKE %:keyword%) ORDER BY service_no DESC LIMIT :limit OFFSET :offset", nativeQuery = true)
    List<ServiceItem> findByKeywordWithRange(
            @Param("offset") int offset,
            @Param("limit") int limit,
            @Param("keyword") String keyword,
            @Param("categoryCd") int categoryCd);

}
