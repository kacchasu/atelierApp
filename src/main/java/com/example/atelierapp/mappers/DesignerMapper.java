package com.example.atelierapp.mappers;

import com.example.atelierapp.dtos.DesignerDTO;
import com.example.atelierapp.dtos.ItemDTO;
import com.example.atelierapp.models.Category;
import com.example.atelierapp.models.Collection;
import com.example.atelierapp.models.Designer;
import com.example.atelierapp.models.Item;
import com.example.atelierapp.services.DesignerService;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class DesignerMapper {
    private final DesignerService designerService;
    public DesignerMapper(DesignerService designerService) {
        this.designerService = designerService;
    }

    public DesignerDTO toDesignerDTO(Designer designer) {
        DesignerDTO designerDTO = new DesignerDTO(designer.getName(), designer.getDescription());
        designerDTO.setId(designerDTO.getId());
        return designerDTO;
    }

    public Designer toDesigner(DesignerDTO designerDTO) {
        Designer designer = new Designer(designerDTO.getName(), designerDTO.getDescription(), null, null);
        return designer;
    }
}
