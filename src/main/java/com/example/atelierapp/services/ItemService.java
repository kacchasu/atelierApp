package com.example.atelierapp.services;

import com.example.atelierapp.exceptions.ResourceNotFoundException;
import com.example.atelierapp.models.Item;
import com.example.atelierapp.repositories.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    public Item getItemById(Long id) {
        return itemRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Item id: " + id));
    }

    public Item createItem(Item item) {
        return itemRepository.save(item);
    }

    public Item updateItem(Long id, Item updatedItem) {
        Item item = getItemById(id);
        item.setName(updatedItem.getName());
        item.setDescription(updatedItem.getDescription());
        item.setPrice(updatedItem.getPrice());
        item.setCategories(updatedItem.getCategories());
        item.setDesigner(updatedItem.getDesigner());
        return itemRepository.save(item);
    }

    public void deleteItem(Long id) {
        Item item = getItemById(id);
        itemRepository.delete(item);
    }

}
