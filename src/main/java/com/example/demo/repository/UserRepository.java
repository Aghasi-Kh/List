package com.example.demo.repository;

import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
    public interface UserRepository extends JpaRepository<User,Long>{
        @Query("select u from User u where u.id = ?1")
        User getById(Long id);

        @Query("Delete from User where id = ?1")
        User deleteUserById(Long id);

        @Query("Select u from User u")
        List<User> users();
    }

