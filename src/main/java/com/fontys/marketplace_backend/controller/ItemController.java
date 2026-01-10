package com.fontys.marketplace_backend.controller;

import lombok.RequiredArgsConstructor;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.fontys.marketplace_backend.exceptions.NotFoundException;
import com.fontys.marketplace_backend.persistence.entity.Item;
import com.fontys.marketplace_backend.persistence.repository.ItemRepository;

@RequiredArgsConstructor
@CrossOrigin(origins = { "http://localhost:5173", "http://localhost" })
@RestController
public class ItemController {
    private final ItemRepository itemRepository;

    @GetMapping("/items/{itemId}")
    Item getItemById(@PathVariable Integer itemId) {
        return itemRepository.findById(itemId)
                .orElseThrow(NotFoundException::new);
    }

    @GetMapping("/items")
    Iterable<Item> getItems() {
        return itemRepository.findAll();
    }

    @GetMapping("/user/items/{userId}")
    Iterable<Item> getItemsByUserId(@PathVariable Integer userId) {
        return itemRepository.findAllBySellerUserId(userId);
    }

    @PostMapping("/items")
    Item createItem(@RequestBody Item item) {
        return itemRepository.save(item);
    }

    @PutMapping("/items/{itemId}")
    ResponseEntity<Item> updateItem(@PathVariable Integer itemId, @RequestBody Item item) {
        Optional<Item> existingItemOptional = itemRepository.findById(itemId);

        if (existingItemOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Item existingItem = existingItemOptional.get();

        if (item.getTitle() != null) {
            existingItem.setTitle(item.getTitle());
        }
        if (item.getDescription() != null) {
            existingItem.setDescription(item.getDescription());
        }
        if (item.getPrice() != null) {
            existingItem.setPrice(item.getPrice());
        }
        if (item.getCategory() != null) {
            existingItem.setCategory(item.getCategory());
        }
        if (item.getQuality() != null) {
            existingItem.setQuality(item.getQuality());
        }
        if (item.getLocation() != null) {
            existingItem.setLocation(item.getLocation());
        }

        return ResponseEntity.ok(itemRepository.save(existingItem));
    }

    @DeleteMapping("/user/items/{itemId}")
    void deleteItem(@PathVariable("itemId") Integer itemId) {
        itemRepository.deleteById(itemId);
    }
}
