package com.example.atelierapp.services;

import com.example.atelierapp.dtos.CategoryDTO;
import com.example.atelierapp.exceptions.ResourceNotFoundException;
import com.example.atelierapp.mappers.CategoryMapper;
import com.example.atelierapp.models.Category;
import com.example.atelierapp.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public List<CategoryDTO> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        List<CategoryDTO> categoryDTOs = new ArrayList<>();
        for (Category category : categories) {
            categoryDTOs.add(categoryMapper.toCategoryDTO(category));
        }
        return categoryDTOs;
    }

    public CategoryDTO getCategoryById(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category id: " + id));
        return categoryMapper.toCategoryDTO(category);
    }

    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        Category category = categoryMapper.toCategory(categoryDTO);
        Category createdCategory = categoryRepository.save(category);
        return categoryMapper.toCategoryDTO(createdCategory);
    }

    public CategoryDTO updateCategory(Long id, CategoryDTO updatedCategoryDTO) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category id: " + id));
        Category updatedCategory = categoryMapper.toCategory(updatedCategoryDTO);
        updatedCategory.setCollections(category.getCollections());
        updatedCategory.setItems(category.getItems());
        updatedCategory.setId(id);
        categoryRepository.save(updatedCategory);
        return updatedCategoryDTO;
    }

    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }

}
