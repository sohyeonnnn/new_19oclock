package com.refactoring.ilgusi.infrastructure.repository.jpa.favorite;


import com.refactoring.ilgusi.domain.favorite.FavoriteRepository;
import com.refactoring.ilgusi.domain.service.Service;

import java.util.ArrayList;

public class JpaFavoriteRepository implements FavoriteRepository {
    private final SpringDataFavoriteRepository jpaRepository;

    public JpaFavoriteRepository(SpringDataFavoriteRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

}
