package com.refactoring.ilgusi.presentation.home;


import com.refactoring.ilgusi.domain.category.interfaces.CategoryService;
import com.refactoring.ilgusi.domain.member.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.Optional;


@RequiredArgsConstructor
@Controller
public class HomeController {

    private final HttpSession httpsession;
    private final CategoryService categoryService;

    @GetMapping("/")
    public String index(Model model){
        /*List<MainCategoryDto> categoryList = categoryService.selectCategoryList();
        model.addAttribute("categoryList", categoryList);*/

        Optional.ofNullable((Member) httpsession.getAttribute("loginMember"))
                .map(member -> {
                    model.addAttribute("loginMember", member);
                    model.addAttribute("isAdmin", member.isAdmin());
                    return member;
                })
                .orElse(null);

        return "home";
    }

}
