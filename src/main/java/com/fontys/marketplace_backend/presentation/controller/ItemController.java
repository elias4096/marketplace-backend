package com.fontys.marketplace_backend.presentation.controller;

import lombok.RequiredArgsConstructor;

import java.util.Optional;

import org.springframework.web.bind.annotation.*;

import com.fontys.marketplace_backend.persistence.entity.Item;
import com.fontys.marketplace_backend.persistence.repository.ItemRepository;
import com.fontys.marketplace_backend.persistence.requests.DeleteItemRequest;

@RestController
@CrossOrigin(origins = "http://localhost:5173/")
@RequiredArgsConstructor
public class ItemController {
    private final ItemRepository itemRepository;

    @GetMapping("/items/{itemId}")
    private Optional<Item> getItemById(@PathVariable Integer itemId) {
        return itemRepository.findById(itemId);
    }

    @GetMapping("/items")
    private Iterable<Item> getItems() {
        return itemRepository.findAll();
    }

    @GetMapping("/user/items/{userId}")
    private Iterable<Item> getItemsByUserId(@PathVariable Integer userId) {
        return itemRepository.findAllBySellerUserId(userId);
    }

    @PostMapping("/items")
    private Item createItem(@RequestBody Item item) {
        return itemRepository.save(item);
    }

    @PutMapping("/user/items")
    private Item updateItem(@RequestBody Item item) {
        return itemRepository.save(item);
    }

    @DeleteMapping("/user/items")
    private void deleteItem(@RequestBody DeleteItemRequest request) {
        itemRepository.deleteById(request.getItemId());
    }
}
