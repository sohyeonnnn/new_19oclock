package com.refactoring.ilgusi.presentation.favorite;

import com.refactoring.ilgusi.domain.favorite.interfaces.FavoriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;


@RequiredArgsConstructor
@Controller
public class FavoriteController {

    private final HttpSession httpsession;
    private final FavoriteService favoriteService;

    @ResponseBody
    @PostMapping("/insertHeart")
    public void insertHeart(int serviceNo, int memberNo){
        System.out.println("controller");
        System.out.println("serviceNo >> " + serviceNo);
        System.out.println("memberNo >> " + memberNo);
        //favoriteService.insertHeart(serviceNo, memberNo);
    }

    @ResponseBody
    @PostMapping("/deleteHeart")
    public void deleteHeart(int serviceNo, int memberNo){
        //favoriteService.deleteHeart(serviceNo, memberNo);

    }

    /*

    // 사용자 마이페이지-찜한 리스트 이동(정렬)
    @RequestMapping("/userHeartList.do")
    public String userHeartList(int mNo, String order, Model model) {
        ArrayList<Service> list = service.selectHeartList(mNo, order);
        ArrayList<String> brandnameList = service.selectBrandName(mNo, order);

        //천원단위
        DecimalFormat df = new DecimalFormat("###,###");
        for(int i=0; i<list.size(); i++) {
            list.get(i).setSPriceTxt(df.format(list.get(i).getSPrice())+"원");
        }

        model.addAttribute("list", list);
        model.addAttribute("brandList", brandnameList);
        model.addAttribute("order", order);
        System.out.println("크기 : " + brandnameList.size());
        for(int i=0; i<brandnameList.size();i++) {
            System.out.println(brandnameList.get(i));
        }
        return "member/userHeartList";
    }

   */

}


