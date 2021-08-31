package com.example.demo.controller;

import com.example.demo.model.Product;
import com.example.demo.service.impl.ProductServiceImpl;
import com.example.demo.util.exception.ProductIsDuplicateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("")
public class ProductController {
    @Autowired
    ProductServiceImpl productServiceImpl;
    @GetMapping("products")
    List<Product> products(@RequestParam(name = "lowerPrice") int lowerPrice,@RequestParam(name = "upperPrice") int upperPrice){
       return productServiceImpl.productsWithThisRange(lowerPrice,upperPrice);
    }

    @Transactional
    @PostMapping("products")
    public Product addProduct(@Valid @RequestBody Product product) throws ProductIsDuplicateException {
        Optional<Product> product1 = productServiceImpl.findById(product.getMId());
        if(product1.isPresent()){
            throw  new ProductIsDuplicateException();
        }
        return productServiceImpl.save(product);
    }
}
