package com.example.demo.product;

import com.example.demo.entities.Dto.ProductDto;
import com.example.demo.entities.Dto.ProductResponseDto;
import com.example.demo.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    Page<ProductResponseDto> findAll(Pageable pageable);
    ProductResponseDto findById(Long id);
    ProductResponseDto save(ProductDto product);
    ProductResponseDto update(Long id, ProductDto productDto);
    ProductResponseDto delete(Long id);

}
