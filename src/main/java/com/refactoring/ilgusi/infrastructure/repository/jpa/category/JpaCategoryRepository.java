package com.refactoring.ilgusi.infrastructure.repository.jpa.category;

import com.refactoring.ilgusi.domain.category.Category;
import com.refactoring.ilgusi.domain.category.CategoryRank;
import com.refactoring.ilgusi.domain.category.CategoryRepository;

import java.util.List;

// 🔸 공통 인터페이스를 구현하고 내부에서 Spring Data JPA를 사용

//@Repository
public class JpaCategoryRepository implements CategoryRepository {

    private final SpringDataCategoryRepository jpaRepository;
    public JpaCategoryRepository(SpringDataCategoryRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }


}