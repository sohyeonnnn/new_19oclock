package com.refactoring.ilgusi.presentation.category;


import com.refactoring.ilgusi.domain.category.Category;
import com.refactoring.ilgusi.domain.category.dto.MainCategoryDto;
import com.refactoring.ilgusi.domain.category.interfaces.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.List;


@RequiredArgsConstructor
@Controller
public class CategoryController {
    private final CategoryService categoryService;

    @RequestMapping(value = "/categoryLoad")
    @ResponseBody
    public List<MainCategoryDto> categoryAjax() {
        return categoryService.selectCategoryList();
    }
    
}

