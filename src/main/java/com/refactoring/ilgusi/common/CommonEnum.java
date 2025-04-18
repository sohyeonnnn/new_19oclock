package com.refactoring.ilgusi.common;

import lombok.Getter;

@Getter
public enum CommonEnum {

    HOME_URL("/"),
    SUCCESS("성공!"),
    FAIL("실패!"),
    ERROR("ERROR! 관리자에게 문의하세요"),

    LOGIN("로그인"),
    LOGOUT("로그아웃"),
    JOIN("회원가입"),
    FAIL_CHANGE_PW("비밀번호 변경 실패"),
    ID("ID"),
    PW("비밀번호"),
    UPDATE_SUCCESS("변경 성공"),

    ALREADY_USED_ID("이미 사용중인 아이디입니다."),
    NOT_VALID_ID("일치하는 id가 없습니다."),
    NOT_VALID_USER("일치하는 회원 정보가 없습니다."),
    LOGIN_FAIL("아이디 또는 비밀번호가 잘못되었습니다."),
    JOIN_FAIL("아이디가 중복되었거나 필수값이 누락되었습니다."),


    ID_IS("ID : "),
    PW_IS("PW : ")







    ;


    CommonEnum(String val) {
        this.val = val;
    }

    private final String val;

}
