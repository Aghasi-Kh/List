package com.example.demo.repository;

import com.example.demo.model.Category;
import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {


    @Query("SELECT u FROM Category u WHERE u.id = ?1")
    Category getById(@Param("id") Long id);

    @Query("select u from  Category u ")
    List<Category> findAll();

    @Query("Delete from Category where id = ?1")
    void deleteCategoryById(Long id);

    //@Query(value = "INSERT into Category (name,filter,parent_id) VALUES (:name,:filter,:parent_id)",nativeQuery = true)
    // int saveCategory(@Param("name")String name, @Param("filter")String filter,@Param("parent_id")Long parent_id);



}
