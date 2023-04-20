package com.example.atelierapp.controllers;

import com.example.atelierapp.dtos.CollectionDTO;
import com.example.atelierapp.mappers.CollectionMapper;
import com.example.atelierapp.models.Collection;
import com.example.atelierapp.models.Item;
import com.example.atelierapp.services.CollectionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/collections")
public class CollectionController {
    private final CollectionService collectionService;
    private final CollectionMapper collectionMapper;

    public CollectionController(CollectionService collectionService, CollectionMapper collectionMapper) {
        this.collectionService = collectionService;
        this.collectionMapper = collectionMapper;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CollectionDTO> getCollectionById(@PathVariable Long id) {
        Collection collection = collectionService.getCollectionById(id);
        CollectionDTO collectionDTO = collectionMapper.toCollectionDTO(collection);
        return ResponseEntity.ok(collectionDTO);
    }

    @GetMapping("")
    public ResponseEntity<List<CollectionDTO>> getAllCollections() {
        List<Collection> collections = collectionService.getAllCollections();
        List<CollectionDTO> collectionDTOs = new ArrayList<>();
        for(Collection collection : collections) {
            collectionDTOs.add(collectionMapper.toCollectionDTO(collection));
        }
        return ResponseEntity.ok(collectionDTOs);
    }

    @PostMapping("")
    public ResponseEntity<CollectionDTO> createCollection(@RequestBody CollectionDTO collectionDTO) {
        Collection collection = collectionMapper.toCollection(collectionDTO);
        Collection savedCollection = collectionService.createCollection(collection);
        CollectionDTO savedCollectionDTO = collectionMapper.toCollectionDTO(savedCollection);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCollectionDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CollectionDTO> updateCollection(@PathVariable Long id, @RequestBody CollectionDTO collectionDTO) {
        Collection collection = collectionMapper.toCollection(collectionDTO);
        collection.setId(id);
        Collection updatedCollection = collectionService.updateCollection(id, collection);
        CollectionDTO updatedCollectionDTO = collectionMapper.toCollectionDTO(updatedCollection);
        return ResponseEntity.ok(updatedCollectionDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCollection(@PathVariable Long id) {
        collectionService.deleteCollection(id);
        return ResponseEntity.noContent().build();
    }
}
