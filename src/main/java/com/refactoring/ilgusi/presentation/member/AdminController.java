package com.refactoring.ilgusi.presentation.member;

import com.refactoring.ilgusi.domain.member.interfaces.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;


@RequiredArgsConstructor
@Controller
public class AdminController {
    private final MemberService memberService;


    /*else {// 관리자가 삭제할때
            //memberService.setDeleteStatusY(mNo); // delete_status = 'y'로 바꿈
    //memberService.deleteMember(mNo);
    loc = "/manageMember?reqPage=1&grade=black&keyword=&order=new";
        */
    
}

