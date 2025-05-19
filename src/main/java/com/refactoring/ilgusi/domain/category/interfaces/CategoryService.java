package com.refactoring.ilgusi.domain.category.interfaces;


import com.refactoring.ilgusi.domain.category.Category;
import com.refactoring.ilgusi.domain.category.dto.MainCategoryDto;

import java.util.List;

public interface CategoryService {
    List<MainCategoryDto> selectCategoryList();


}
