package com.example.demo.services;

import com.example.demo.Dto.category.CategoryDto;
import com.example.demo.Dto.category.CategoryResponseDto;

import java.util.List;

public interface CategoryService {

    List<CategoryResponseDto> listAll();
    CategoryResponseDto findById(Long id);
    CategoryResponseDto save(CategoryDto categoryDto);
    CategoryResponseDto update(Long id, CategoryDto categoryDto);
    CategoryResponseDto delete(Long id);
    CategoryResponseDto findByName(String name);

}
