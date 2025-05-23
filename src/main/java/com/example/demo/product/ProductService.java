package com.example.demo.product;

import com.example.demo.entities.Dto.ProductDto;
import com.example.demo.entities.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<Product> findAll();
    Optional<Product> findById(Long id);
    Product save(ProductDto product);
    Optional<Product> update(Long id, Product product);
    Optional<Product> delete(Long id);

}
