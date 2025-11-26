package com.fontys.marketplace_backend.presentation.controller;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import com.fontys.marketplace_backend.NotFoundException;
import com.fontys.marketplace_backend.persistence.entity.Item;
import com.fontys.marketplace_backend.persistence.repository.ItemRepository;

@RestController
@CrossOrigin
@RequiredArgsConstructor
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

    @PutMapping("/user/items")
    Item updateItem(@RequestBody Item item) {
        return itemRepository.save(item);
    }

    @DeleteMapping("/user/items/{itemId}")
    void deleteItem(@PathVariable Integer itemId) {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(NotFoundException::new);

        itemRepository.delete(item);
    }
}
