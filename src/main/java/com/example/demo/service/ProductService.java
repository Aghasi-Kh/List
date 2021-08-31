package com.example.demo.service;

import com.example.demo.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> productsWithThisRange(int lowerPrice, int upperPrice);
    Optional<Product> findById(Long id);
    Product save(Product product);
}
