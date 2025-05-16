package com.refactoring.ilgusi.domain.favorite.interfaces;

import com.refactoring.ilgusi.domain.member.Member;

public interface FavoriteService {
    boolean isFavoriteChecked(int memberNo, int serviceNo);
}
