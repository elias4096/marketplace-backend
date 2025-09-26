package com.fontys.backend.controller;

import com.fontys.backend.model.Item;
import com.fontys.backend.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:5173/")
@RequiredArgsConstructor
@RestController
public class ItemController {
    private final ItemRepository itemRepository;

    @GetMapping("/api/items")
    private Iterable<Item> getItems() {
        return itemRepository.findAll();
    }

    @GetMapping("/api/user/items")
    private Iterable<Item> getUserItems(@RequestBody long userId) {
        return itemRepository.findAllBySellerUserId(userId);
    }

    @PostMapping("/api/items")
    private Item createItem(@RequestBody Item item) {
        return itemRepository.save(item);
    }

    @PutMapping("/api/items")
    private Item updateItem(@RequestBody Item item) {
        return itemRepository.save(item);
    }

    @DeleteMapping("/api/items")
    private void deleteItem(@RequestBody long itemID) {
        itemRepository.deleteById(itemID);
    }
}
