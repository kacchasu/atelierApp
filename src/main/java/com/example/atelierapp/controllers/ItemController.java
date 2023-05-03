package com.example.atelierapp.controllers;

import com.example.atelierapp.dtos.ItemDTO;
import com.example.atelierapp.mappers.ItemMapper;
import com.example.atelierapp.models.Item;
import com.example.atelierapp.services.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RepositoryRestController
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;
    private final ItemMapper itemMapper;

    @GetMapping("/items/{id}")
    public ResponseEntity<ItemDTO> getItem(@PathVariable Long id) {
        Item item = itemService.getItemById(id);
        ItemDTO itemDTO = itemMapper.toItemDTO(item);
        return ResponseEntity.ok(itemDTO);
    }

    @GetMapping("/items")
    public ResponseEntity<List<ItemDTO>> getAllItems() {
        List<Item> items = itemService.getAllItems();
        List<ItemDTO> itemDTOs = new ArrayList<>();
        for (Item item : items) {
            itemDTOs.add(itemMapper.toItemDTO(item));
        }
        return ResponseEntity.ok(itemDTOs);
    }

    @PostMapping("/items")
    public ResponseEntity<ItemDTO> createItem(@RequestBody ItemDTO itemDTO) {
        Item item = itemMapper.toItem(itemDTO);
        Item savedItem = itemService.createItem(item);
        ItemDTO savedItemDTO = itemMapper.toItemDTO(savedItem);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedItemDTO);
    }

    @PutMapping("/items/{id}")
    public ResponseEntity<ItemDTO> updateItem(@PathVariable Long id, @RequestBody ItemDTO itemDTO) {
        Item item = itemMapper.toItem(itemDTO);
        item.setId(id);
        Item updatedItem = itemService.updateItem(id, item);
        ItemDTO updatedItemDTO = itemMapper.toItemDTO(updatedItem);
        return ResponseEntity.ok(updatedItemDTO);
    }

    @DeleteMapping("/items/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long id) {

        itemService.deleteItem(id);
        return ResponseEntity.noContent().build();
    }
}
