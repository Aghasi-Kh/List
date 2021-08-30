package com.example.demo.controller;

import com.example.demo.model.Category;
import com.example.demo.service.impl.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CategoryController {

    private CategoryServiceImpl categoryService;

    public CategoryController(CategoryServiceImpl categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/category/{id}")
    public Category getCategory(@PathVariable Long id){
        return categoryService.getById(id);
    }
    @GetMapping("/category")
    public List<Category> getAllCategories(){
        return categoryService.getAll();
    }
    @PostMapping("/category")
    public ResponseEntity save( @RequestBody Category category){
        categoryService.save(category);
        return ResponseEntity.ok(category);
    }


}
