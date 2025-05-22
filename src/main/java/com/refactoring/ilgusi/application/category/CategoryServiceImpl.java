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
        return mapToDto(categoryList);
    }

    @Override
    public List<MainCategoryDto> selectMainCategoryList(int mainCategoryCd) {
        List<Category> categoryList = categoryRepository.selectMainCategoryList(mainCategoryCd);
        return mapToDto(categoryList);
    }

    private List<MainCategoryDto> mapToDto(List<Category> categoryList) {
        List<Category> mainCategories = categoryList.stream()
                .filter(c -> c.getCategoryCd().equals(c.getMainCategoryCd()))
                .collect(Collectors.toList());

        return mainCategories.stream().map(parent -> {
            List<SubCategoryDto> subList = categoryList.stream()
                    .filter(c -> !c.getCategoryCd().equals(c.getMainCategoryCd()))
                    .filter(c -> c.getMainCategoryCd().equals(parent.getCategoryCd()))
                    .map(sub -> SubCategoryDto.builder()
                            .mainCategoryCd(sub.getMainCategoryCd())
                            .categoryCd(sub.getCategoryCd())
                            .categoryNm(sub.getCategoryName())
                            .imgUrl(sub.getImgUrl())
                            .build())
                    .collect(Collectors.toList());

            return MainCategoryDto.builder()
                    .mainCategoryCd(parent.getCategoryCd())
                    .categoryNm(parent.getCategoryName())
                    .imgUrl(parent.getImgUrl())
                    .subCategoryList(subList)
                    .build();
        }).collect(Collectors.toList());
    }

}
