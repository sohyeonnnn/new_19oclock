package com.refactoring.ilgusi.infrastructure.repository.jpa.favorite;


import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.refactoring.ilgusi.domain.category.QCategory;
import com.refactoring.ilgusi.domain.favorite.Favorite;
import com.refactoring.ilgusi.domain.favorite.QFavorite;
import com.refactoring.ilgusi.domain.favorite.interfaces.FavoriteQRepository;
import com.refactoring.ilgusi.domain.favorite.interfaces.FavoriteRepository;
import com.refactoring.ilgusi.domain.member.QMember;
import com.refactoring.ilgusi.domain.service.QServiceItem;
import com.refactoring.ilgusi.domain.service.dto.QServiceInfoDto;
import com.refactoring.ilgusi.domain.service.dto.ServiceInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class FavoriteQRepositoryImpl implements FavoriteQRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<ServiceInfoDto> selectFavoriteList(int memberNo, String order) {
        QFavorite favorite = QFavorite.favorite;
        QServiceItem service = QServiceItem.serviceItem;
        QMember user = new QMember("user");
        QMember free = new QMember("free");
        QCategory mainCat = new QCategory("mainCat");
        QCategory subCat = new QCategory("subCat");

        OrderSpecifier<?> orderSpecifier = getOrderSpecifier(order, service);

        return queryFactory
                .select(new QServiceInfoDto(
                        service.serviceNo,
                        service.serviceTitle,
                        service.serviceContent,
                        service.servicePrice,
                        service.serviceArea,
                        service.serviceImg,
                        service.serviceRate,
                        service.mainCategory,
                        service.subCategory,
                        service.workingDate,
                        service.workingCount,
                        service.writeDate,
                        service.deleteStatus,
                        service.adminApproval,
                        free.memberNo,
                        free.memberId,
                        free.brandName,
                        free.introduce,
                        mainCat.categoryName,
                        subCat.categoryName
                ))
                .from(service)
                .leftJoin(favorite).on(service.serviceNo.eq(favorite.service.serviceNo))
                .leftJoin(user).on(favorite.member.memberNo.eq(user.memberNo))
                .leftJoin(free).on(service.member.memberNo.eq(free.memberNo))
                .leftJoin(mainCat).on(service.mainCategory.eq(mainCat.categoryCd))
                .leftJoin(subCat).on(service.subCategory.eq(subCat.categoryCd))
                .where(
                        favorite.member.memberNo.eq(memberNo),
                        service.adminApproval.eq("Y")
                )
                .orderBy(orderSpecifier)
                .fetch();
    }

    private OrderSpecifier<?> getOrderSpecifier(String order, QServiceItem service) {
        if ("priceDown".equals(order)) {
            return service.servicePrice.asc();
        } else if ("priceUp".equals(order)) {
            return service.servicePrice.desc();
        } else {
            return service.createdDate.desc();
        }
    }


}
