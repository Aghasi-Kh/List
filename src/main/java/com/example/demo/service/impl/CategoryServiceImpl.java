package com.example.demo.service.impl;

import com.example.demo.model.Category;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.service.CategoryService;
import com.example.demo.util.exception.DuplicateDataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {


    @Autowired
    CategoryRepository categoryRepository;


    @Override
    public void save(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public Category getById(Long id) {
        return categoryRepository.getById(id);
    }


    @Override
    public List<Category> getAll() {
        return categoryRepository.findAll();

    }

    @Override
    public void update(Category category) {
        categoryRepository.save(category);
    }


}
