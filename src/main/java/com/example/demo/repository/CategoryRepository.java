package com.example.demo.repository;

import com.example.demo.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(path = "category", itemResourceRel = "category", collectionResourceRel = "category")
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query(value = "SELECT * FROM category WHERE id = ?1", nativeQuery = true)
    Category getById(@Param("id") Long id);

    @Query("select u from  Category u ")
    List<Category> findAll();

    @Query("Delete from Category where id = ?1")
    void deleteCategoryById(Long id);

    @Query(value = "INSERT into category (name, filter, parent_id) VALUES (:obj.mName,:obj.mFilter,:obj.mParent_id)", nativeQuery = true)
    int saveCategory(@Param("obj") Category category);
}
