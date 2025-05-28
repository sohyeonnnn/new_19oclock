package com.refactoring.ilgusi.domain.favorite.interfaces;


import com.refactoring.ilgusi.domain.favorite.Favorite;
import com.refactoring.ilgusi.domain.service.dto.ServiceInfoDto;

import java.util.List;
import java.util.Optional;

public interface FavoriteQRepository {
    List<ServiceInfoDto> selectFavoriteList(int memberNo, String order);
}

