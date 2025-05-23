package com.example.demo.category;

import com.example.demo.entities.Category;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {


    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Category> listAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Optional<Category> findById(Long id) {
        return Optional.empty();
    }

    @Override
    @Transactional
    public Category save(Category category) {
        return categoryRepository.save(category);


    }

    @Override
    public Optional<Category> update(Long id, Category category) {
        return Optional.empty();
    }

    @Override
    public Optional<Category> delete(Long id) {
        return Optional.empty();
    }
}
