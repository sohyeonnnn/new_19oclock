package com.refactoring.ilgusi.infrastructure.repository.jpa.favorite;


import com.refactoring.ilgusi.domain.favorite.Favorite;
import com.refactoring.ilgusi.domain.favorite.interfaces.FavoriteRepository;

public class JpaFavoriteRepository implements FavoriteRepository {
    private final SpringDataFavoriteRepository jpaRepository;

    public JpaFavoriteRepository(SpringDataFavoriteRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Favorite searchFavorite(int memberNo, int serviceNo) {
        return null;
    }
}
