package com.refactoring.ilgusi.domain.category.interfaces;


import com.refactoring.ilgusi.domain.category.dto.MainCategoryDto;

import java.util.List;


public interface CategoryService {
    List<MainCategoryDto> selectCategoryList();


}
