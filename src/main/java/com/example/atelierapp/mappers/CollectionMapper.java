package com.example.atelierapp.mappers;

import com.example.atelierapp.dtos.CollectionDTO;
import com.example.atelierapp.models.Category;
import com.example.atelierapp.models.Collection;
import com.example.atelierapp.models.Designer;
import com.example.atelierapp.models.Item;
import com.example.atelierapp.services.CategoryService;
import com.example.atelierapp.services.DesignerService;
import com.example.atelierapp.services.ItemService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class CollectionMapper {
    private final DesignerService designerService;
    private final ItemService itemService;
    private final CategoryService categoryService;

    public CollectionMapper(DesignerService designerService, ItemService itemService, CategoryService categoryService) {
        this.designerService = designerService;
        this.itemService = itemService;
        this.categoryService = categoryService;
    }
    public CollectionDTO toCollectionDTO(Collection collection) {
        CollectionDTO collectionDTO = new CollectionDTO(collection.getName(), collection.getDescription(),
                collection.getDesigner().getId(), null, null);
        Set<Long> itemIds = new HashSet<>();
        for(Item item : collection.getItems()) {
            itemIds.add(item.getId());
        }
        Set<Long> categoryIds = new HashSet<>();
        for(Category category: collection.getCategories()) {
            categoryIds.add(category.getId());
        }
        collectionDTO.setCategoryIds(categoryIds);
        collectionDTO.setItemIds(itemIds);
        collectionDTO.setId(collection.getId());
        return collectionDTO;
    }

    public Collection toCollection(CollectionDTO collectionDTO) {
        Collection collection = new Collection(collectionDTO.getName(), collectionDTO.getDescription(), null,
                null, null);
        Set<Category> categories = new HashSet<>();
        for(Long categoryId : collectionDTO.getCategoryIds()) {
            categories.add(categoryService.getCategoryById(categoryId));
        }
        Set<Item> items = new HashSet<>();
        for(Long itemId : collectionDTO.getItemIds()) {
            items.add(itemService.getItemById(itemId));
        }
        collection.setDesigner(designerService.getDesignerById(collectionDTO.getDesignerId()));
        collection.setCategories(categories);
        collection.setItems(items);
        return collection;
    }

}
