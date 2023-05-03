package com.example.atelierapp.mappers;

import com.example.atelierapp.dtos.CategoryDTO;
import com.example.atelierapp.models.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {
    public Category toCategory(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setName(categoryDTO.getName());
        return category;
    }

    public CategoryDTO toCategoryDTO(Category category) {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setName(category.getName());
        categoryDTO.setId(category.getId());
        return categoryDTO;
    }

}
