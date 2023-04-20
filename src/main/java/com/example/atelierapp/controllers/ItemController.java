package com.example.atelierapp.controllers;

import com.example.atelierapp.dtos.ItemDTO;
import com.example.atelierapp.mappers.ItemMapper;
import com.example.atelierapp.models.Item;
import com.example.atelierapp.services.ItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemController {

    private final ItemService itemService;
    private final ItemMapper itemMapper;

    public ItemController(ItemService itemService, ItemMapper itemMapper) {
        this.itemService = itemService;
        this.itemMapper = itemMapper;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemDTO> getItem(@PathVariable Long id) {
        Item item = itemService.getItemById(id);
        ItemDTO itemDTO = itemMapper.toItemDTO(item);
        return ResponseEntity.ok(itemDTO);
    }

    @GetMapping
    public ResponseEntity<List<ItemDTO>> getAllItems() {
        List<Item> items = itemService.getAllItems();
        List<ItemDTO> itemDTOs = new ArrayList<>();
        for(Item item : items) {
            itemDTOs.add(itemMapper.toItemDTO(item));
        }
        return ResponseEntity.ok(itemDTOs);
    }

    @PostMapping
    public ResponseEntity<ItemDTO> createItem(@RequestBody ItemDTO itemDTO) {
        Item item = itemMapper.toItem(itemDTO);
        Item savedItem = itemService.createItem(item);
        ItemDTO savedItemDTO = itemMapper.toItemDTO(savedItem);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedItemDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemDTO> updateItem(@PathVariable Long id, @RequestBody ItemDTO itemDTO) {
        Item item = itemMapper.toItem(itemDTO);
        item.setId(id);
        Item updatedItem = itemService.updateItem(id, item);
        ItemDTO updatedItemDTO = itemMapper.toItemDTO(updatedItem);
        return ResponseEntity.ok(updatedItemDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long id) {

        itemService.deleteItem(id);
        return ResponseEntity.noContent().build();
    }
}
