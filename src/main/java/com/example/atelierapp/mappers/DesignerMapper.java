package com.example.atelierapp.mappers;

import com.example.atelierapp.dtos.DesignerDTO;
import com.example.atelierapp.exceptions.ResourceNotFoundException;
import com.example.atelierapp.models.Category;
import com.example.atelierapp.models.Designer;
import com.example.atelierapp.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class DesignerMapper {
    private final CategoryRepository categoryRepository;
    public DesignerDTO toDesignerDTO(Designer designer) {
        DesignerDTO designerDTO = new DesignerDTO();
        designerDTO.setName(designer.getName());
        Set<Long> categoryIds = new HashSet<>();
        for (Category category : designer.getCategories()) {
            categoryIds.add(category.getId());
        }
        designerDTO.setCategoryIds(categoryIds);
        designerDTO.setDescription(designer.getDescription());
        designerDTO.setId(designer.getId());
        return designerDTO;
    }

    public Designer toDesigner(DesignerDTO designerDTO) {
        Designer designer = new Designer();
        designer.setName(designerDTO.getName());
        Set<Category> categories = new HashSet<>();
        for (Long categoryId : designerDTO.getCategoryIds()) {
            categories.add(categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category id: " + categoryId)));
        }
        designer.setCategories(categories);
        designer.setDescription(designerDTO.getDescription());
        return designer;
    }
}
