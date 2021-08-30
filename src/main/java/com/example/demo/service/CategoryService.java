package com.example.demo.service;

import com.example.demo.model.Category;
import org.springframework.scheduling.annotation.Async;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CategoryService {

    Category getById(Long id);

    List<Category> getAll();

    @Transactional
    @Async("taskExecutorSecond")
    void update(Category category);
}
