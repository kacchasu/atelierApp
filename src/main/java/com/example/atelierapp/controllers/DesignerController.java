package com.example.atelierapp.controllers;

import com.example.atelierapp.dtos.DesignerDTO;
import com.example.atelierapp.mappers.DesignerMapper;
import com.example.atelierapp.models.Designer;
import com.example.atelierapp.models.Item;
import com.example.atelierapp.services.DesignerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/designers")
public class DesignerController {
    private final DesignerService designerService;
    private final DesignerMapper designerMapper;

    public DesignerController(DesignerService designerService, DesignerMapper designerMapper) {
        this.designerService = designerService;
        this.designerMapper = designerMapper;
    }

    @GetMapping("/{id}")
    public ResponseEntity<DesignerDTO> getDesignerById(@PathVariable Long id) {
        Designer designer = designerService.getDesignerById(id);
        DesignerDTO designerDTO = designerMapper.toDesignerDTO(designer);
        return ResponseEntity.ok(designerDTO);
    }

    @GetMapping("")
    public ResponseEntity<List<DesignerDTO>> getAllDesigners() {
        List<Designer> designers = designerService.getAllDesigners();
        List<DesignerDTO> designerDTOs = new ArrayList<>();
        for(Designer designer : designers) {
            designerDTOs.add(designerMapper.toDesignerDTO(designer));
        }
        return ResponseEntity.ok(designerDTOs);
    }

    @PostMapping("")
    public ResponseEntity<DesignerDTO> createDesigner(@RequestBody DesignerDTO designerDTO) {
        Designer designer = designerMapper.toDesigner(designerDTO);
        Designer savedDesigner = designerService.createDesigner(designer);
        DesignerDTO savedDesignerDTO = designerMapper.toDesignerDTO(savedDesigner);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedDesignerDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DesignerDTO> updateDesigner(@PathVariable Long id, @RequestBody DesignerDTO designerDTO) {
        Designer designer = designerMapper.toDesigner(designerDTO);
        designer.setId(id);
        designer.setCollections(designerService.getDesignerById(id).getCollections());
        designer.setItems(designerService.getDesignerById(id).getItems());
        Designer updatedDesigner = designerService.updateDesigner(id, designer);
        DesignerDTO updatedDesignerDTO = designerMapper.toDesignerDTO(updatedDesigner);
        return ResponseEntity.ok(updatedDesignerDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDesigner(@PathVariable Long id) {
        designerService.deleteDesigner(id);
        return ResponseEntity.noContent().build();
    }
}
