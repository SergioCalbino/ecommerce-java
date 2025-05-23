package com.example.demo.category;

import com.example.demo.entities.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    List<Category> listAll();
    Optional<Category> findById(Long id);
    Category save(Category category);
    Optional<Category> update(Long id, Category category);
    Optional<Category> delete(Long id);

}
