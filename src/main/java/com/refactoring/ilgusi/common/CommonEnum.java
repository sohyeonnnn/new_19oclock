package com.refactoring.ilgusi.common;

import lombok.Getter;

@Getter
public enum CommonEnum {

    SUCCESS("성공!"),
    FAIL("실패!"),
    ERROR("ERROR! 관리자에게 문의하세요"),
    ALREADY_USED_ID("이미 사용중인 아이디입니다."),
    LOGIN_FAIL("로그인 실패"),
    NOT_VALID_ID("일치하는 id가 없습니다.");

    CommonEnum(String val) {
        this.val = val;
    }

    private final String val;

}
