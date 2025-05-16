package com.refactoring.ilgusi.domain.favorite.interfaces;


import com.refactoring.ilgusi.domain.favorite.Favorite;

public interface FavoriteRepository {
    Favorite searchFavorite(int memberNo, int serviceNo);
}

