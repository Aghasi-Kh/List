package com.example.demo.service;

import com.example.demo.model.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<Product> productsWithThisRange(int lowerPrice, int upperPrice);


    Product getById(@Param("id") Long id);


    List<Product> findAll();

    void deleteProductById(Long id);

    Optional<Product> findById(Long id);

    Product save(Product product);
}
