package com.example.atelierapp.services;

import com.example.atelierapp.exceptions.ResourceNotFoundException;
import com.example.atelierapp.models.Designer;
import com.example.atelierapp.repositories.DesignerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DesignerService {

    private final DesignerRepository designerRepository;

    public List<Designer> getAllDesigners() {
        return designerRepository.findAll();
    }

    public Designer getDesignerById(Long id) {
        return designerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Designer id: " + id));
    }

    public Designer createDesigner(Designer designer) {
        return designerRepository.save(designer);
    }

    public Designer updateDesigner(Long id, Designer updatedDesigner) {
        Designer designer = getDesignerById(id);
        designer.setName(updatedDesigner.getName());
        designer.setDescription(updatedDesigner.getDescription());
        return designerRepository.save(designer);
    }

    public void deleteDesigner(Long id) {
        Designer designer = getDesignerById(id);
        designerRepository.delete(designer);
    }
}
