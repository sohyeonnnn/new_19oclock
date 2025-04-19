package com.refactoring.ilgusi.domain.category.interfaces;


import com.refactoring.ilgusi.domain.category.Category;
import com.refactoring.ilgusi.domain.category.CategoryRank;

import java.util.List;

// 모든 DB 기술에서 공통으로 사용할 비즈니스 인터페이스
public interface CategoryRepository {
    List<Category> selectCategoryList();
    List<CategoryRank> selectCategoryRankList(int cNo);
}
