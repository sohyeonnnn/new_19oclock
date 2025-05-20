package com.refactoring.ilgusi.domain.category.interfaces;


import com.refactoring.ilgusi.domain.category.Category;
import com.refactoring.ilgusi.domain.category.CategoryRank;

import java.util.List;

public interface CategoryRepository {
    List<Category> selectCategoryList();
    List<Category> selectMainCategoryList(int mainCategoryCd);
    List<CategoryRank> selectCategoryRankList(int cNo);
}
