package com.refactoring.ilgusi.infrastructure.repository.jpa.favorite;

import com.refactoring.ilgusi.domain.favorite.Favorite;
import com.refactoring.ilgusi.domain.service.dto.ServiceInfoDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SpringDataFavoriteRepository extends JpaRepository<Favorite, Integer> {

    @Query(
            "SELECT new com.refactoring.ilgusi.domain.service.dto.ServiceInfoDto(" +
                    "s.serviceNo, s.serviceTitle, s.serviceContent, s.servicePrice, s.serviceArea, " +
                    "s.serviceImg, s.serviceRate, s.mainCategory, s.subCategory, s.workingDate, " +
                    "s.workingCount, s.writeDate, s.deleteStatus, s.adminApproval, " +
                    "s.member.memberNo, m.memberId, m.brandName, m.introduce, mainCat.categoryName, subCat.categoryName) " +
                    "FROM ServiceItem s " +
                    "LEFT JOIN Favorite f ON s.serviceNo = f.service.serviceNo " +
                    "LEFT JOIN Member m ON f.member.memberNo = m.memberNo " +
                    "LEFT JOIN Category mainCat ON s.mainCategory = mainCat.categoryCd " +
                    "LEFT JOIN Category subCat ON s.subCategory = subCat.categoryCd " +
                    "WHERE f.member.memberNo = :memberNo " +
                    "AND s.adminApproval = :isApproved " +
                    "ORDER BY s.createdDate DESC"
    )
    List<ServiceInfoDto> selectFavoriteByMemberNoOrderByDate(int memberNo, @Param("isApproved") String isApproved);

    @Query(
            "SELECT new com.refactoring.ilgusi.domain.service.dto.ServiceInfoDto(" +
                    "s.serviceNo, s.serviceTitle, s.serviceContent, s.servicePrice, s.serviceArea, " +
                    "s.serviceImg, s.serviceRate, s.mainCategory, s.subCategory, s.workingDate, " +
                    "s.workingCount, s.writeDate, s.deleteStatus, s.adminApproval, " +
                    "s.member.memberNo, m.memberId, m.brandName, m.introduce, mainCat.categoryName, subCat.categoryName) " +
                    "FROM ServiceItem s " +
                    "LEFT JOIN Favorite f ON s.serviceNo = f.service.serviceNo " +
                    "LEFT JOIN Member m ON f.member.memberNo = m.memberNo " +
                    "LEFT JOIN Category mainCat ON s.mainCategory = mainCat.categoryCd " +
                    "LEFT JOIN Category subCat ON s.subCategory = subCat.categoryCd " +
                    "WHERE f.member.memberNo = :memberNo " +
                    "AND s.adminApproval = :isApproved " +
                    "ORDER BY s.servicePrice ASC"
    )
    List<ServiceInfoDto> selectFavoriteByMemberNoOrderByPriceAsc(int memberNo, @Param("isApproved") String isApproved);

    @Query(
            "SELECT new com.refactoring.ilgusi.domain.service.dto.ServiceInfoDto(" +
                    "s.serviceNo, s.serviceTitle, s.serviceContent, s.servicePrice, s.serviceArea, " +
                    "s.serviceImg, s.serviceRate, s.mainCategory, s.subCategory, s.workingDate, " +
                    "s.workingCount, s.writeDate, s.deleteStatus, s.adminApproval, " +
                    "s.member.memberNo, m.memberId, m.brandName, m.introduce, mainCat.categoryName, subCat.categoryName) " +
                    "FROM ServiceItem s " +
                    "LEFT JOIN Favorite f ON s.serviceNo = f.service.serviceNo " +
                    "LEFT JOIN Member m ON f.member.memberNo = m.memberNo " +
                    "LEFT JOIN Category mainCat ON s.mainCategory = mainCat.categoryCd " +
                    "LEFT JOIN Category subCat ON s.subCategory = subCat.categoryCd " +
                    "WHERE f.member.memberNo = :memberNo " +
                    "AND s.adminApproval = :isApproved " +
                    "ORDER BY s.servicePrice DESC"
    )
    List<ServiceInfoDto> selectFavoriteByMemberNoOrderByPriceDesc(int memberNo, @Param("isApproved") String isApproved);
}
