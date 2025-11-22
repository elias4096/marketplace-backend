package com.fontys.marketplace_backend.presentation.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fontys.marketplace_backend.persistence.entity.Category;
import com.fontys.marketplace_backend.persistence.entity.Condition;

@RestController
@CrossOrigin(origins = { "http://localhost:5173/", "http://localhost/", "http://localhost:80/" })
@RequiredArgsConstructor
public class StaticController {
    @GetMapping("/static/categories")
    private Category[] getCategories() {
        return Category.values();
    }

    @GetMapping("/static/conditions")
    private Condition[] getConditions() {
        return Condition.values();
    }
}
