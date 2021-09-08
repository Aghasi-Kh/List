package com.example.demo.service.impl;

import com.example.demo.dto.CategoryFilter;
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

    private final CategoryRepository categoryRepository;

    @PersistenceContext
    private EntityManager entitymanager;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Transactional
    @Override
    public void save(Category category) {
        entitymanager.createNativeQuery("INSERT into category (name, parent_id,filter ::jsonb) VALUES (?,?,?)")
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
    public int saveCategory(String name, CategoryFilter filter){
        if ( categoryRepository.saveCategory(name,filter)){
            return 1;
        }
        return 0;
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
