package com.refactoring.ilgusi.application.category;


import com.refactoring.ilgusi.domain.category.Category;
import com.refactoring.ilgusi.domain.category.dto.MainCategoryDto;
import com.refactoring.ilgusi.domain.category.dto.SubCategoryDto;
import com.refactoring.ilgusi.domain.category.interfaces.CategoryRepository;
import com.refactoring.ilgusi.domain.category.interfaces.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Transactional
@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    /*@Override
    public List<MainCategoryDto> selectCategoryList() {
        List<Category> categoryList = categoryRepository.selectCategoryList();
        Map<Integer, MainCategoryDto> mainCategoryMap = new HashMap<>();
        for (Category category : categoryList) {
            if(category.getCDivisionNo().equals(category.getCNo())){
                MainCategoryDto mainCategory= MainCategoryDto.builder()
                        .cDivisionNo(category.getCDivisionNo())
                        .cDivisionName(category.getCName())
                        .subCategoryList(new ArrayList<>())
                        .build();
                mainCategoryMap.put(category.getCDivisionNo(), mainCategory);
            }else{
                SubCategoryDto subCategory = SubCategoryDto.builder()
                        .cNo(category.getCNo())
                        .cName(category.getCName())
                        .imgUrl(category.getImgUrl())
                        .build();
                MainCategoryDto mainCategory = mainCategoryMap.get(category.getCDivisionNo());
                if (mainCategory != null) {
                    mainCategory.getSubCategoryList().add(subCategory);
                }
            }
        }
        return  new ArrayList<>(mainCategoryMap.values());
    }*/
}
