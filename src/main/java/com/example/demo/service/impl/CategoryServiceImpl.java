package com.example.demo.service.impl;

import com.example.demo.model.Category;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @PersistenceContext
    private EntityManager entitymanager;

    @Transactional
    @Override
    public void save(Category category) {
        entitymanager.createNativeQuery("INSERT into category (name, filter, parent_id) VALUES (?,?,?)")
                .setParameter(1, category.getMName())
                .setParameter(2, category.getMFilter())
                .setParameter(3, category.getMParent_Id())
                .executeUpdate();
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
