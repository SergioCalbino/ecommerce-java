package com.example.demo.services;

import com.example.demo.Dto.ProductDto;
import com.example.demo.Dto.ProductResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {

    Page<ProductResponseDto> findAll(Pageable pageable);
    ProductResponseDto findById(Long id);
    ProductResponseDto save(ProductDto product);
    ProductResponseDto update(Long id, ProductDto productDto);
    ProductResponseDto delete(Long id);
    ProductResponseDto findByName(String name);


}
