package com.refactoring.ilgusi.infrastructure.repository.jpa.favorite;


import com.refactoring.ilgusi.domain.favorite.Favorite;
import com.refactoring.ilgusi.domain.favorite.interfaces.FavoriteRepository;
import com.refactoring.ilgusi.domain.service.dto.ServiceInfoDto;

import java.util.List;
import java.util.Optional;

public class JpaFavoriteRepository implements FavoriteRepository {
    private final SpringDataFavoriteRepository jpaRepository;

    public JpaFavoriteRepository(SpringDataFavoriteRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Optional<Favorite> searchFavorite(int memberNo, int serviceNo) {
        return null;
    }

    @Override
    public void insertFavorite(Favorite favorite) {
        jpaRepository.save(favorite);
    }

    @Override
    public void deleteHeart(Favorite favorite) {
        jpaRepository.delete(favorite);
    }

    @Override
    public List<ServiceInfoDto> selectHeartList(int memberNo, String order) {
        if(order.equals("new")){
            return jpaRepository.selectFavoriteByMemberNoOrderByDate(memberNo, "Y");
        }else if(order.equals("priceDown")){
            return jpaRepository.selectFavoriteByMemberNoOrderByPriceAsc(memberNo, "Y");
        }else if(order.equals("priceUp")) {
            return jpaRepository.selectFavoriteByMemberNoOrderByPriceDesc(memberNo, "Y");
        }
        return null;
    }
}
