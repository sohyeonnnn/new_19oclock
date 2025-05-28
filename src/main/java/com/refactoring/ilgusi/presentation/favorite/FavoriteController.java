package com.refactoring.ilgusi.presentation.favorite;

import com.refactoring.ilgusi.domain.favorite.interfaces.FavoriteService;
import com.refactoring.ilgusi.domain.member.Member;
import com.refactoring.ilgusi.domain.service.dto.ServiceInfoDto;
import com.refactoring.ilgusi.domain.service.interfaces.ServiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;


@RequiredArgsConstructor
@Controller
public class FavoriteController {

    private final HttpSession httpsession;
    private final FavoriteService favoriteService;

    @ResponseBody
    @PostMapping("/insertHeart")
    public void insertHeart(int serviceNo, int memberNo){
        favoriteService.insertHeart(serviceNo, memberNo);
    }

    @ResponseBody
    @PostMapping("/deleteHeart")
    public void deleteHeart(int serviceNo, int memberNo){
        favoriteService.deleteHeart(serviceNo, memberNo);
    }

    @GetMapping("/userHeartList")
    public String userHeartList(@ModelAttribute("loginMember") Member m, @RequestParam String order, Model model) {
        List<ServiceInfoDto> list = favoriteService.selectHeartList(m.getMemberNo(), order);
        model.addAttribute("list", list);
        model.addAttribute("order", order);
        return "member/userHeartList";
    }

}


