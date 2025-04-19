package com.refactoring.ilgusi.presentation.category;


import com.refactoring.ilgusi.domain.category.interfaces.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;


@RequiredArgsConstructor
@Controller
public class CategoryController {
    private final CategoryService categoryService;

/*    @PostMapping("/categoryMainLoad")
    @ResponseBody
    public List<MainCategoryDto>  index(Model model) {
        System.out.println(categoryService.selectCategoryList().toString());
        return categoryService.selectCategoryList();
    }*/

    
}

