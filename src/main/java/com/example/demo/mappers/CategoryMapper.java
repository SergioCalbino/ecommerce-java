package com.example.demo.mappers;

import com.example.demo.Dto.CategoryResponseDto;
import com.example.demo.entities.Category;
import com.example.demo.Dto.ProductResponseDto;

import java.util.List;
import java.util.stream.Collectors;

public class CategoryMapper {

    public static CategoryResponseDto toDto(Category category) {

        CategoryResponseDto categoryResponseDto = new CategoryResponseDto();
        categoryResponseDto.setId(category.getId());
        categoryResponseDto.setName(category.getName());
        List<ProductResponseDto> productResponseDtos = category.getProducts()
                .stream().map(product -> ProductMapper.toDto(product))
                .collect(Collectors.toList());
        categoryResponseDto.setProductResponseDtoList(productResponseDtos);

        return categoryResponseDto;


    }

    public static CategoryResponseDto toCreateDto(Category category){
        CategoryResponseDto categoryResponseDto = new CategoryResponseDto();
        categoryResponseDto.setName(category.getName());

        return categoryResponseDto;
    }

    public static CategoryResponseDto toUpdateDto(Category category){
        CategoryResponseDto categoryResponseDto = new CategoryResponseDto();
        categoryResponseDto.setName(category.getName());

        return categoryResponseDto;
    }

    public static CategoryResponseDto toDeleteDto(Category category) {
        CategoryResponseDto categoryResponseDto = new CategoryResponseDto();
        categoryResponseDto.setId(category.getId());
        categoryResponseDto.setName(category.getName());
        return categoryResponseDto;
    }


}
