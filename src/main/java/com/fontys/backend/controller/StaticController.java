package com.fontys.backend.controller;

import com.fontys.backend.model.Category;
import com.fontys.backend.model.Condition;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:5173/")
@RequiredArgsConstructor
@RestController
public class StaticController {
    @GetMapping("/api/static/categories")
    private Category[] getCategories() {
        return Category.values();
    }

    @GetMapping("/api/static/conditions")
    private Condition[] getConditions() {
        return Condition.values();
    }
}
