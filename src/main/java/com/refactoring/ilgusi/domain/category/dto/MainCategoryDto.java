package com.refactoring.ilgusi.domain.category.dto;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Builder
@Data
@RequiredArgsConstructor
public class MainCategoryDto {
    private final int ParentCategoryCd;
    private final String categoryNm;
    private final List<SubCategoryDto> subCategoryList;

}
