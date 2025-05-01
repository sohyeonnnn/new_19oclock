package com.refactoring.ilgusi.domain.category.dto;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Builder
@Data
@RequiredArgsConstructor
public class MainCategoryDto {
    private final int cDivisionNo;
    private final String cDivisionName;
    private final List<SubCategoryDto> subCategoryList;

    @Override
    public String toString() {
        return "MainCategoryDto{" +
                "cDivisionNo=" + cDivisionNo +
                ", cDivisionName='" + cDivisionName + '\'' +
                ", subCategoryList=" + subCategoryList +
                '}';
    }
}
