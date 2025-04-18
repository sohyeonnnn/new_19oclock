package com.refactoring.ilgusi.presentation.home;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;


@RequiredArgsConstructor
@Controller
public class HomeController {

    private final HttpSession httpsession;

    @GetMapping("/")
    public String index(Model model){

        return "home";
    }

}
