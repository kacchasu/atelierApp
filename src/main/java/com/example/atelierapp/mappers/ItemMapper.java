package com.example.atelierapp.mappers;

import com.example.atelierapp.dtos.ItemDTO;
import com.example.atelierapp.exceptions.ResourceNotFoundException;
import com.example.atelierapp.models.Category;
import com.example.atelierapp.models.Item;
import com.example.atelierapp.repositories.CategoryRepository;
import com.example.atelierapp.services.DesignerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class ItemMapper {
    private final CategoryRepository categoryRepository;
    private final DesignerService designerService;

    public ItemDTO toItemDTO(Item item) {
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setName(item.getName());
        itemDTO.setDescription(item.getDescription());
        itemDTO.setPrice(item.getPrice());
        itemDTO.setDesignerId(item.getDesigner().getId());
        itemDTO.setId(item.getId());
        Set<Long> categoryIds = new HashSet<>();
        for (Category category : item.getCategories()) {
            categoryIds.add(category.getId());
        }
        itemDTO.setCategoryIds(categoryIds);
        return itemDTO;
    }

    public Item toItem(ItemDTO itemDTO) {
        Item item = new Item();
        item.setName(itemDTO.getName());
        item.setDescription(itemDTO.getDescription());
        item.setPrice(itemDTO.getPrice());
        Set<Category> categories = new HashSet<>();
        for (Long categoryId : itemDTO.getCategoryIds()) {
            Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category id: " + categoryId));
            categories.add(category);
        }

        item.setCategories(categories);
        item.setDesigner(designerService.getDesignerById(itemDTO.getDesignerId()));
        return item;
    }
}
