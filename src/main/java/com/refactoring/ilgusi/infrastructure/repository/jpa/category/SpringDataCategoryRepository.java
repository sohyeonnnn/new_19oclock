package com.refactoring.ilgusi.infrastructure.repository.jpa.category;

import com.refactoring.ilgusi.domain.category.Category;
import com.refactoring.ilgusi.domain.category.CategoryRank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpringDataCategoryRepository extends JpaRepository<Category, Integer>{

}
