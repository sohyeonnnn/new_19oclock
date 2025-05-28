package com.refactoring.ilgusi.domain.favorite.interfaces;


import com.refactoring.ilgusi.domain.favorite.Favorite;
import com.refactoring.ilgusi.domain.service.dto.ServiceInfoDto;

import java.util.List;
import java.util.Optional;

public interface FavoriteRepository {
    Optional<Favorite> searchFavorite(int memberNo, int serviceNo);
    void insertFavorite(Favorite favorite);
    void deleteHeart(Favorite favorite);
    List<ServiceInfoDto> selectHeartList(int memberNo, String order);
}

