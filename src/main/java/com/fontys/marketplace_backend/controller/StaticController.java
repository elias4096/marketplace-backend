package com.fontys.marketplace_backend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fontys.marketplace_backend.persistence.entity.Category;
import com.fontys.marketplace_backend.persistence.entity.Condition;

@RestController
@CrossOrigin
@RequiredArgsConstructor
public class StaticController {
    @GetMapping("/static/categories")
    Category[] getCategories() {
        return Category.values();
    }

    @GetMapping("/static/conditions")
    Condition[] getConditions() {
        return Condition.values();
    }
}
