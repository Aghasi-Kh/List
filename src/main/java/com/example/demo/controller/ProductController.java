package com.example.demo.controller;

import com.example.demo.model.Product;
import com.example.demo.service.impl.ProductServiceImpl;
import com.example.demo.util.exception.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

import static com.example.demo.util.constants.Message.NOT_FOUND_MESSAGE;

@RestController
@RequestMapping("/")
public class ProductController {
    @Autowired
    ProductServiceImpl productServiceImpl;

    @GetMapping("productsRange")
    List<Product> products(@RequestParam(name = "lowerPrice") int lowerPrice, @RequestParam(name = "upperPrice") int upperPrice) {
        return productServiceImpl.productsWithThisRange(lowerPrice, upperPrice);
    }

    @GetMapping("products")
    List<Product> allProducts() {
        return productServiceImpl.findAll();
    }

    @GetMapping("products/{id}")
    public Optional<Product> getProductById(@PathVariable Long id) throws DataNotFoundException {
        Optional<Product> product = productServiceImpl.findById(id);
        if (product.isEmpty()) {
            throw new DataNotFoundException("There is no product with id-" + id);
        }
        return product;
    }

    @Transactional
    @PostMapping("products")
    public Product addProduct(@Valid @RequestBody Product product) throws DataNotFoundException {
        Optional<Product> product1 = productServiceImpl.findById(product.getMId());
        if (product1.isPresent()) {
            throw new DataNotFoundException(NOT_FOUND_MESSAGE);
        }
        return productServiceImpl.save(product);
    }

    @Transactional
    @DeleteMapping("users/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productServiceImpl.deleteProductById(id);
    }

}
