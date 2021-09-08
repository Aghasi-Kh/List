package com.example.demo.controller;

import com.example.demo.model.Product;
import com.example.demo.service.impl.ProductServiceImpl;
import com.example.demo.util.exception.DataNotFoundException;
import com.example.demo.util.exception.DuplicateDataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

import static com.example.demo.util.constants.Message.*;

@RestController
@RequestMapping("/")
public class ProductController {
    private final ProductServiceImpl productServiceImpl;

    @Autowired
    public ProductController(ProductServiceImpl productServiceImpl) {
        this.productServiceImpl = productServiceImpl;
    }

    @GetMapping("productsRange")
    public ResponseEntity products(@RequestParam(name = "lowerPrice") int lowerPrice, @RequestParam(name = "upperPrice") int upperPrice) {
        return ResponseEntity.ok().body(productServiceImpl.productsWithThisRange(lowerPrice, upperPrice));
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
    public ResponseEntity addProduct(@Valid @RequestBody Product product) throws DuplicateDataException {
        Optional<Product> product1 = productServiceImpl.findById(product.getMId());
        if (product1.isPresent()) {
            throw new DuplicateDataException(DUPLICATE_PRODUCT_MESSAGE);
        }
        return ResponseEntity.ok().body(productServiceImpl.save(product));
    }

    @Transactional
    @DeleteMapping("users/{id}")
    public ResponseEntity deleteProduct(@PathVariable Long id) {
        return ResponseEntity.ok().body(productServiceImpl.deleteProductById(id));
    }

}
