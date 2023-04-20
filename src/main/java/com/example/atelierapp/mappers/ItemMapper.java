package com.example.atelierapp.mappers;

import com.example.atelierapp.dtos.ItemDTO;
import com.example.atelierapp.models.Category;
import com.example.atelierapp.models.Collection;
import com.example.atelierapp.models.Item;
import com.example.atelierapp.services.CategoryService;

import com.example.atelierapp.services.CollectionService;
import com.example.atelierapp.services.DesignerService;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class ItemMapper {
    private final CategoryService categoryService;
    private final DesignerService designerService;
    private final CollectionService collectionService;

    public ItemMapper(CategoryService categoryService, DesignerService designerService, CollectionService collectionService) {
        this.categoryService = categoryService;
        this.designerService = designerService;
        this.collectionService = collectionService;
    }

    public ItemDTO toItemDTO(Item item) {
        ItemDTO itemDTO = new ItemDTO(item.getName(), item.getDescription(), item.getPrice(), item.getDesigner().getId(), null);
        itemDTO.setId(item.getId());
        Set<Long> categoryIds = new HashSet<>();
        for(Category category : item.getCategories()) {
            categoryIds.add(category.getId());
        }
        itemDTO.setCategoryIds(categoryIds);
        return itemDTO;
    }

    public Item toItem(ItemDTO itemDTO) {
        Item item = new Item(itemDTO.getName(), itemDTO.getDescription(), itemDTO.getPrice(), null, null, null);
        Set<Category> categories = new HashSet<>();
        for(Long categoryId : itemDTO.getCategoryIds()) {
            Category category = categoryService.getCategoryById(categoryId);
            categories.add(category);
        }

        item.setCategories(categories);
        item.setDesigner(designerService.getDesignerById(itemDTO.getDesignerId()));
        return item;
    }
}
