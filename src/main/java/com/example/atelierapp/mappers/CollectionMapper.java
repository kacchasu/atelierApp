package com.example.atelierapp.mappers;

import com.example.atelierapp.dtos.CollectionDTO;
import com.example.atelierapp.exceptions.ResourceNotFoundException;
import com.example.atelierapp.models.Category;
import com.example.atelierapp.models.Collection;
import com.example.atelierapp.models.Item;
import com.example.atelierapp.repositories.CategoryRepository;
import com.example.atelierapp.services.DesignerService;
import com.example.atelierapp.services.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class CollectionMapper {
    private final DesignerService designerService;
    private final ItemService itemService;
    private final CategoryRepository categoryRepository;

    public CollectionDTO toCollectionDTO(Collection collection) {
        CollectionDTO collectionDTO = new CollectionDTO();
        collectionDTO.setName(collection.getName());
        collectionDTO.setDescription(collection.getDescription());
        collectionDTO.setDesignerId(collection.getDesigner().getId());

        Set<Long> itemIds = new HashSet<>();
        for (Item item : collection.getItems()) {
            itemIds.add(item.getId());
        }
        Set<Long> categoryIds = new HashSet<>();
        for (Category category : collection.getCategories()) {
            categoryIds.add(category.getId());
        }
        collectionDTO.setCategoryIds(categoryIds);
        collectionDTO.setItemIds(itemIds);
        collectionDTO.setId(collection.getId());
        return collectionDTO;
    }

    public Collection toCollection(CollectionDTO collectionDTO) {
        Collection collection = new Collection();
        collection.setName(collectionDTO.getName());
        collection.setDescription(collectionDTO.getDescription());

        Set<Category> categories = new HashSet<>();
        for (Long categoryId : collectionDTO.getCategoryIds()) {
            categories.add(categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category id: " + categoryId)));
        }
        Set<Item> items = new HashSet<>();
        for (Long itemId : collectionDTO.getItemIds()) {
            items.add(itemService.getItemById(itemId));
        }
        collection.setDesigner(designerService.getDesignerById(collectionDTO.getDesignerId()));
        collection.setCategories(categories);
        collection.setItems(items);
        return collection;
    }

}
