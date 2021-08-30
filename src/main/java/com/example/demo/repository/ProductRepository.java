package com.example.demo.repository;

import com.example.demo.model.Product;
import lombok.Builder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository  extends JpaRepository<Product,Long> {
    @Query("select p from Product p where p.mPrice >= :lowerPrice and p.mPrice <= :upperPrice")
    List<Product> productsWithThisRange(int lowerPrice, int upperPrice);
}
