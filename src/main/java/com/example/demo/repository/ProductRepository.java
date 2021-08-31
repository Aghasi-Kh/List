package com.example.demo.repository;

import com.example.demo.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("select p from Product p where p.mPrice >= :lowerPrice and p.mPrice <= :upperPrice")
    List<Product> productsWithThisRange(int lowerPrice, int upperPrice);

    @Query("SELECT p FROM Product p WHERE p.id = ?1")
    Product getById(@Param("id") Long id);

    @Query("select p from  Product p ")
    List<Product> findAll();

    @Query("Delete from Product where id = ?1")
    void deleteProductById(Long id);
}
