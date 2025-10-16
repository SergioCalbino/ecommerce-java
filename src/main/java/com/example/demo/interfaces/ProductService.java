package com.example.demo.interfaces;

import com.example.demo.Dto.product.ProductDto;
import com.example.demo.Dto.product.ProductResponseDto;
import com.example.demo.Dto.product.ProductUpdateDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {

    Page<ProductResponseDto> findAll(String name, Pageable pageable);
    Page<ProductResponseDto> findAllActive(String name, Pageable pageable);
    ProductResponseDto findById(Long id);
    ProductResponseDto save(ProductDto product);
    ProductResponseDto update(Long id, ProductUpdateDto productDto);
    void delete(Long id);
    ProductResponseDto findByName(String name);


}
