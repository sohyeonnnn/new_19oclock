package com.refactoring.ilgusi.domain.member;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum RoleEnum {

    ADMIN(0, "관리자"),
    USER(1, "일반회원"),
    FREELANCER(2, "프리랜서");

    private final Integer key;
    private final String value;

}
