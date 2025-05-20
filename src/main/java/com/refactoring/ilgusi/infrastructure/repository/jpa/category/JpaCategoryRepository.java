package com.refactoring.ilgusi.infrastructure.repository.jpa.category;

import com.refactoring.ilgusi.domain.category.Category;
import com.refactoring.ilgusi.domain.category.CategoryRank;
import com.refactoring.ilgusi.domain.category.interfaces.CategoryRepository;

import java.util.List;

//@Repository
public class JpaCategoryRepository implements CategoryRepository {

    private final SpringDataCategoryRepository jpaRepository;
    public JpaCategoryRepository(SpringDataCategoryRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public List<Category> selectCategoryList() {
        return jpaRepository.findAll();
    }

    @Override
    public List<Category> selectMainCategoryList(int mainCategoryCd) {
        return jpaRepository.selectMainCategoryList(mainCategoryCd);
    }

    @Override
    public List<CategoryRank> selectCategoryRankList(int cNo) {
        return List.of();
    }
}