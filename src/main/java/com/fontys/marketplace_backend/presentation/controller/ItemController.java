package com.fontys.marketplace_backend.presentation.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import com.fontys.marketplace_backend.persistence.entity.Item;
import com.fontys.marketplace_backend.persistence.repository.ItemRepository;

@RestController
@CrossOrigin(origins = "http://localhost:5173/")
@RequiredArgsConstructor
public class ItemController {
    private final ItemRepository itemRepository;

    @GetMapping("/items")
    private Iterable<Item> getItems() {
        return itemRepository.findAll();
    }

    @GetMapping("/user/items")
    private Iterable<Item> getUserItems(@RequestBody Integer userId) {
        return itemRepository.findAllBySellerUserId(userId);
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
    private void deleteItem(@RequestBody Integer itemID) {
        itemRepository.deleteById(itemID);
    }
}
