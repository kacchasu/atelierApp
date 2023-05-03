package com.example.atelierapp.services;

import com.example.atelierapp.exceptions.ResourceNotFoundException;
import com.example.atelierapp.models.Collection;
import com.example.atelierapp.repositories.CollectionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CollectionService {

    private final CollectionRepository collectionRepository;

    public List<Collection> getAllCollections() {
        return collectionRepository.findAll();
    }

    public Collection getCollectionById(Long id) {
        return collectionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Collection id: " + id));
    }

    public Collection createCollection(Collection collection) {
        return collectionRepository.save(collection);
    }

    public Collection updateCollection(Long id, Collection updatedCollection) {
        Collection collection = getCollectionById(id);
        collection.setName(updatedCollection.getName());
        collection.setDescription(updatedCollection.getDescription());
        collection.setDesigner(updatedCollection.getDesigner());
        collection.setItems(updatedCollection.getItems());
        collection.setCategories(updatedCollection.getCategories());
        return collectionRepository.save(collection);
    }

    public void deleteCollection(Long id) {
        Collection collection = getCollectionById(id);
        collectionRepository.delete(collection);
    }

    public Collection findCollectionByName(String name) {
        return collectionRepository.findByName(name).orElseThrow(() -> new ResourceNotFoundException("Collection name: " + name));
    }
}
