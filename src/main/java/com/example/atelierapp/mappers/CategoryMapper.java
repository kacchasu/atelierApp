package com.example.atelierapp.mappers;

import com.example.atelierapp.dtos.CategoryDTO;
import com.example.atelierapp.models.Category;
import com.example.atelierapp.models.Collection;
import com.example.atelierapp.services.CollectionService;
import com.example.atelierapp.services.ItemService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class CategoryMapper {
    public Category toCategory(CategoryDTO categoryDTO) {
        return new Category(categoryDTO.getName(), null, null);
   }
   public CategoryDTO toCategoryDTO(Category category) {
        CategoryDTO categoryDTO = new CategoryDTO(category.getName());
        categoryDTO.setId(category.getId());
        return categoryDTO;
   }

}
