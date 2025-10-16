package com.example.demo.mappers;

import com.example.demo.Dto.product.ProductDto;
import com.example.demo.Dto.product.ProductResponseDto;
import com.example.demo.entities.Category;
import com.example.demo.entities.Product;

public class ProductMapper {

    public static ProductResponseDto toDto(Product product) {
        ProductResponseDto dto = new ProductResponseDto();

        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setPrice(product.getPrice());
        dto.setDescription(product.getDescription());
        dto.setImage(product.getImage());
        dto.setStock(product.getStock());
        dto.setCategoryId(product.getCategory().getId());
        dto.setCategoryName(product.getCategory().getName());

        dto.setIsActive(product.isActive());

        return dto;
    }

    public static ProductResponseDto toDtoCustomer(Product product) {
        ProductResponseDto dto = new ProductResponseDto();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setPrice(product.getPrice());
        dto.setDescription(product.getDescription());
        dto.setImage(product.getImage());
        dto.setStock(product.getStock());
        dto.setCategoryId(product.getCategory().getId());
        dto.setCategoryName(product.getCategory().getName());
        dto.setIsActive(product.isActive());

        return dto;
    }

    public static Product fromDto(ProductDto productDto, Category category) {

        Product product = new Product();

        product.setName(productDto.getName());
        product.setImage(productDto.getImage());
        product.setPrice(productDto.getPrice());
        product.setStock(productDto.getStock());
        product.setDescription(productDto.getDescription());
        product.setCategory(category);

        return product;
    }

}
