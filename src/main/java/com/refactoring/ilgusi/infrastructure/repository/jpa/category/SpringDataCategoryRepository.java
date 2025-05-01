package com.refactoring.ilgusi.infrastructure.repository.jpa.category;

import com.refactoring.ilgusi.domain.category.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataCategoryRepository extends JpaRepository<Category, Integer>{

}
