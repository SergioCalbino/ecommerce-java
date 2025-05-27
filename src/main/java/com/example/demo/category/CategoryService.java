package com.example.demo.category;

import com.example.demo.category.dto.CategoryDto;
import com.example.demo.category.dto.CategoryResponseDto;
import com.example.demo.entities.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    List<CategoryResponseDto> listAll();
    CategoryResponseDto findById(Long id);
    CategoryResponseDto save(CategoryDto categoryDto);
    CategoryResponseDto update(Long id, CategoryDto categoryDto);
    CategoryResponseDto delete(Long id);

}
