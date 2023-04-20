package com.example.atelierapp.controllers;

import com.example.atelierapp.dtos.CategoryDTO;
import com.example.atelierapp.mappers.CategoryMapper;
import com.example.atelierapp.models.Category;
import com.example.atelierapp.models.Item;
import com.example.atelierapp.services.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    public CategoryController(CategoryService categoryService, CategoryMapper categoryMapper) {
        this.categoryService = categoryService;
        this.categoryMapper = categoryMapper;
    }

    @GetMapping("")
    public ResponseEntity<List<CategoryDTO>> getAllCategories() {
        List<Category> categories = categoryService.getAllCategories();
        List<CategoryDTO> categoryDTOs = new ArrayList<>();
        for(Category category : categories) {
            categoryDTOs.add(categoryMapper.toCategoryDTO(category));
        }
        return ResponseEntity.ok().body(categoryDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable Long id) {
        Category category = categoryService.getCategoryById(id);
        CategoryDTO categoryDTO = categoryMapper.toCategoryDTO(category);
        return ResponseEntity.ok().body(categoryDTO);
    }

    @PostMapping("")
    public ResponseEntity<CategoryDTO> createCategory(@RequestBody CategoryDTO categoryDTO) {
        Category category = categoryMapper.toCategory(categoryDTO);
        Category createdCategory = categoryService.createCategory(category);
        CategoryDTO createdCategoryDTO = categoryMapper.toCategoryDTO(createdCategory);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCategoryDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDTO> updateCategory(@PathVariable Long id, @RequestBody CategoryDTO categoryDTO) {
        Category category = categoryMapper.toCategory(categoryDTO);
        category.setCollections(categoryService.getCategoryById(id).getCollections());
        category.setItems(categoryService.getCategoryById(id).getItems());
        category.setId(id);
        Category updatedCategory = categoryService.updateCategory(id, category);
        CategoryDTO updatedCategoryDTO = categoryMapper.toCategoryDTO(updatedCategory);
        return ResponseEntity.ok().body(updatedCategoryDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
}
