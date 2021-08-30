package com.example.demo.controller;

import com.example.demo.model.Category;
import com.example.demo.service.impl.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class CategoryController {

    @Autowired
    CategoryServiceImpl categoryService;

    @GetMapping("/category/{id}")
    public Category printCategory(@PathVariable Long id){
        return categoryService.getById(id);
    }
}
