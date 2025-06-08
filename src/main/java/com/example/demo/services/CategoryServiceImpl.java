package com.example.demo.services;

import com.example.demo.Dto.category.CategoryDto;
import com.example.demo.mappers.CategoryMapper;
import com.example.demo.Dto.category.CategoryResponseDto;
import com.example.demo.entities.Category;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.repositories.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {


    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<CategoryResponseDto> listAll() {
        List<Category> categoryList = categoryRepository.findAll();
        return categoryList.stream().map(CategoryMapper::toDto).toList();
    }

    @Override
    public CategoryResponseDto findById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Category with id " + id + "not found"));
        return CategoryMapper.toDto(category);


    }

    @Override
    @Transactional
    public CategoryResponseDto save(CategoryDto categoryDto) {

       Category newCategory = new Category();
        newCategory.setName(categoryDto.getName());

        Category savedCategory = categoryRepository.save(newCategory);
        return CategoryMapper.toCreateDto(savedCategory);
    }

    @Override
    public CategoryResponseDto update(Long id, CategoryDto categoryDto) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Category with id " + id + " not found"));

        category.setName(categoryDto.getName());
        Category updatedCategory = categoryRepository.save(category);
        return CategoryMapper.toUpdateDto(updatedCategory);


    }

    @Override
    public CategoryResponseDto delete(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Category with id " + id + " not found"));

        CategoryResponseDto deletedCategory = CategoryMapper.toDeleteDto(category);
        categoryRepository.delete(category);
        return deletedCategory;

    }

    @Override
    public CategoryResponseDto findByName(String name) {
        Category category = categoryRepository.findByNameIgnoreCase(name)
                .orElseThrow(() -> new NotFoundException("Category with " + name + "not found"));

        return CategoryMapper.toDto(category);
    }
}
