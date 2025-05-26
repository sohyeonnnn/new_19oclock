package com.refactoring.ilgusi.domain.favorite.interfaces;

public interface FavoriteService {
    boolean isFavoriteChecked(int memberNo, int serviceNo);

    void insertHeart(int serviceNo, int memberNo);
    void deleteHeart(int serviceNo, int memberNo);
}
