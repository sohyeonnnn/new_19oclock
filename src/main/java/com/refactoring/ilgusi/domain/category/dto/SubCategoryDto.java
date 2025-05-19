package com.refactoring.ilgusi.domain.category.dto;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Builder
@Data
@RequiredArgsConstructor
public class SubCategoryDto {
    private final int categoryCd;
    private final String categoryNm;
    private final String imgUrl;
}
