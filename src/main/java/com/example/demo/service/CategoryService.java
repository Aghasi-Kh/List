package com.example.demo.service;

import com.example.demo.dto.CategoryFilter;
import com.example.demo.model.Category;
import org.springframework.scheduling.annotation.Async;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CategoryService {

    void save(Category category);

    Category getById(Long id);

    int saveCategory(String name, CategoryFilter filter);

    List<Category> getAll();

    void update(Category category);
}
