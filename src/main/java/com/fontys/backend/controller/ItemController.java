package com.fontys.backend.controller;

import com.fontys.backend.model.Item;
import com.fontys.backend.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173/")
@RequiredArgsConstructor
public class ItemController {
    private final ItemRepository itemRepository;

    @GetMapping("/items")
    private List<Item> getItems() {
        return itemRepository.findAll();
    }

    @PostMapping("/items")
    private Item createItem(@RequestBody Item item) {
        return itemRepository.save(item);
    }

    @PutMapping("/items")
    private Item updateItem(@RequestBody Item item) {
        return itemRepository.save(item);
    }

    @DeleteMapping("/items")
    private void deleteItem(@RequestBody int itemID) {
        itemRepository.deleteById(itemID);
    }
}
