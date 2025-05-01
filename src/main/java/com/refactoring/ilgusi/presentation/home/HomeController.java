package com.refactoring.ilgusi.presentation.home;


import com.refactoring.ilgusi.domain.category.interfaces.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;


@RequiredArgsConstructor
@Controller
public class HomeController {

    private final HttpSession httpsession;
    private final CategoryService categoryService;

    @GetMapping("/")
    public String index(Model model){
        /*List<MainCategoryDto> categoryList = categoryService.selectCategoryList();
        model.addAttribute("categoryList", categoryList);*/


        return "home";
    }

}


