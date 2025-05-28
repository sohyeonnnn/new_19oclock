package com.refactoring.ilgusi.domain.favorite.interfaces;

import com.refactoring.ilgusi.domain.service.dto.ServiceInfoDto;

import java.util.List;

public interface FavoriteService {
    boolean isFavoriteChecked(int memberNo, int serviceNo);
    void insertHeart(int serviceNo, int memberNo);
    void deleteHeart(int serviceNo, int memberNo);
    List<ServiceInfoDto> selectHeartList(int memberNo, String order);

}
