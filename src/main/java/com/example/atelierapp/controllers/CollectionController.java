package com.example.atelierapp.controllers;

import com.example.atelierapp.dtos.CollectionDTO;
import com.example.atelierapp.mappers.CollectionMapper;
import com.example.atelierapp.models.Collection;
import com.example.atelierapp.services.CollectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RepositoryRestController
@RequiredArgsConstructor
public class CollectionController {
    private final CollectionService collectionService;
    private final CollectionMapper collectionMapper;

    @GetMapping("/collections/{id}")
    public ResponseEntity<CollectionDTO> getCollectionById(@PathVariable Long id) {
        Collection collection = collectionService.getCollectionById(id);
        CollectionDTO collectionDTO = collectionMapper.toCollectionDTO(collection);
        return ResponseEntity.ok(collectionDTO);
    }

    @GetMapping("/collections")
    public ResponseEntity<List<CollectionDTO>> getAllCollections() {
        List<Collection> collections = collectionService.getAllCollections();
        List<CollectionDTO> collectionDTOs = new ArrayList<>();
        for (Collection collection : collections) {
            collectionDTOs.add(collectionMapper.toCollectionDTO(collection));
        }
        return ResponseEntity.ok(collectionDTOs);
    }

    @PostMapping("/collections")
    public ResponseEntity<CollectionDTO> createCollection(@RequestBody CollectionDTO collectionDTO) {
        Collection collection = collectionMapper.toCollection(collectionDTO);
        Collection savedCollection = collectionService.createCollection(collection);
        CollectionDTO savedCollectionDTO = collectionMapper.toCollectionDTO(savedCollection);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCollectionDTO);
    }

    @PutMapping("/collections/{id}")
    public ResponseEntity<CollectionDTO> updateCollection(@PathVariable Long id, @RequestBody CollectionDTO collectionDTO) {
        Collection collection = collectionMapper.toCollection(collectionDTO);
        collection.setId(id);
        Collection updatedCollection = collectionService.updateCollection(id, collection);
        CollectionDTO updatedCollectionDTO = collectionMapper.toCollectionDTO(updatedCollection);
        return ResponseEntity.ok(updatedCollectionDTO);
    }

    @DeleteMapping("/collections/{id}")
    public ResponseEntity<Void> deleteCollection(@PathVariable Long id) {
        collectionService.deleteCollection(id);
        return ResponseEntity.noContent().build();
    }
}
