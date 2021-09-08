package com.example.demo.service.impl;

import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.ProductService;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

   // private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");



    @Override
    public List<Product> productsWithThisRange(int lowerPrice, int upperPrice) {
        return productRepository.productsWithThisRange(lowerPrice, upperPrice);
    }

    @Override
    public Product getById(Long id) {
        return productRepository.getById(id);
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Boolean deleteProductById(Long id) {
       Optional<Product> product= productRepository.findById(id);
        if (product.isPresent()) {
            productRepository.deleteProductById(id);
            return true;
        }
        return  false;

    }

    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public Product save(Product product) {
        Date date = new Date();
        product.setMCreatedDate(date);
        product.setMActiveDate(date);
        return productRepository.save(product);
    }

}
