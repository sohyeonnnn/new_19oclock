package com.refactoring.ilgusi.common;


import org.springframework.ui.Model;

public class MsgRedirectHelper {

    public static String build(Model model, String msg, String loc) {
        model.addAttribute("msg", msg);
        model.addAttribute("loc", loc);
        return "common/msg";
    }

    public static String success(Model model, String msg, String loc) {
        model.addAttribute("msg", msg + " " +CommonEnum.SUCCESS.getVal());
        model.addAttribute("loc", loc);
        return "common/msg";
    }

    public static String fail(Model model, String msg, String loc) {
        model.addAttribute("msg", msg  + " " +CommonEnum.FAIL.getVal());
        model.addAttribute("loc", loc);
        return "common/msg";
    }

    public static String close(Model model, String msg, String loc, boolean close) {
        model.addAttribute("msg", msg);
        model.addAttribute("loc", loc);
        model.addAttribute("close", close);
        return "common/msg2";
    }
}
