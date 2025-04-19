package com.refactoring.ilgusi.domain.category.dto;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Builder
@Data
@RequiredArgsConstructor
public class SubCategoryDto {
    private final int cNo;
    private final String cName;
    private final String imgUrl;
}
