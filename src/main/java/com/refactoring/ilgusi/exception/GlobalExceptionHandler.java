package com.refactoring.ilgusi.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    // CustomException 발생 시
    @ExceptionHandler(CustomException.class)
    public String handleCustomException(CustomException e, Model model) {
        model.addAttribute("msg", "오류 : " + e.getMessage());
        if(e.isClose()){
            model.addAttribute("loc", e.getUrl());
            model.addAttribute("close", e.isClose());
            return "common/msg2"; // 공통 메시지 페이지 (ex: alert 후 리다이렉트)
        }else{
            model.addAttribute("loc", e.getUrl());
            return "common/msg"; // 공통 메시지 페이지 (ex: alert 후 리다이렉트)
        }
    }

    // 그 외의 예외 발생 시
    @ExceptionHandler(Exception.class)
    public String handleException(Exception e, Model model) {
        model.addAttribute("msg", "알 수 없는 오류 발생 : "+e.getMessage());
        model.addAttribute("loc", "/");
        return "common/msg";
    }

}
