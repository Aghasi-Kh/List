package com.example.demo.service;

import com.example.demo.model.Category;

import java.util.List;

public interface CategoryService {

    Category getById(Long id);

    List<Category> getAll();
}
