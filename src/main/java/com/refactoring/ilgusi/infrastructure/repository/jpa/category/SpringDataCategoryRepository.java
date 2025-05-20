package com.refactoring.ilgusi.infrastructure.repository.jpa.category;

import com.refactoring.ilgusi.domain.category.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SpringDataCategoryRepository extends JpaRepository<Category, Integer>{

    @Query("SELECT c FROM Category c WHERE c.mainCategoryCd = :mainCategoryCd")
    List<Category> selectMainCategoryList(int mainCategoryCd);
}
