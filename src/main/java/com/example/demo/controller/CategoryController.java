package com.example.demo.controller;

import com.example.demo.model.Category;
import com.example.demo.service.impl.CategoryServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryController {

    private CategoryServiceImpl categoryService;

    public CategoryController(CategoryServiceImpl categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/category/{id}")
    public Category getCategoryById(@PathVariable Long id) {
        return categoryService.getById(id);
    }

    @GetMapping("/category")
    public List<Category> getAllCategories() {
        return categoryService.getAll();
    }

    @PostMapping("/category")
    public ResponseEntity create(@RequestBody Category category) {
        categoryService.save(category);
        return ResponseEntity.ok(category);
    }

    @PutMapping("/category")
    public ResponseEntity update(@RequestBody Category category) {

        categoryService.update(category);

        return ResponseEntity.ok(category);
    }


}
