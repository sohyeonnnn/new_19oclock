package com.refactoring.ilgusi.infrastructure.repository.jpa.favorite;

import com.refactoring.ilgusi.domain.favorite.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataFavoriteRepository extends JpaRepository<Favorite, Integer> {
}
