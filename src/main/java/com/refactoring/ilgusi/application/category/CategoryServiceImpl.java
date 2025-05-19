package com.refactoring.ilgusi.application.category;


import com.refactoring.ilgusi.domain.category.Category;
import com.refactoring.ilgusi.domain.category.dto.MainCategoryDto;
import com.refactoring.ilgusi.domain.category.dto.SubCategoryDto;
import com.refactoring.ilgusi.domain.category.interfaces.CategoryRepository;
import com.refactoring.ilgusi.domain.category.interfaces.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public List<MainCategoryDto> selectCategoryList() {
        List<Category> categoryList = categoryRepository.selectCategoryList();

        List<Category> parentCategories = categoryList.stream()
                .filter(c -> c.getCategoryCd().equals(c.getParentCategoryCd()))
                .collect(Collectors.toList());

        List<MainCategoryDto> result = parentCategories.stream().map(parent -> {
            List<SubCategoryDto> subList = categoryList.stream()
                    .filter(c -> !c.getCategoryCd().equals(c.getParentCategoryCd()))
                    .filter(c -> c.getParentCategoryCd().equals(parent.getCategoryCd()))
                    .map(sub -> SubCategoryDto.builder()
                            .categoryCd(sub.getCategoryCd())
                            .categoryNm(sub.getCategoryName())
                            .imgUrl(sub.getImgUrl())
                            .build())
                    .collect(Collectors.toList());

            return MainCategoryDto.builder()
                    .ParentCategoryCd(parent.getCategoryCd())
                    .categoryNm(parent.getCategoryName())
                    .subCategoryList(subList)
                    .build();
        }).collect(Collectors.toList());

        return result;
    }
}
