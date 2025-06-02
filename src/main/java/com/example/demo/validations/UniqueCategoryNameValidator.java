package com.example.demo.validations;

import com.example.demo.repositories.CategoryRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;


@Component
public class UniqueCategoryNameValidator implements ConstraintValidator<UniqueCategoryName, String> {


    private final CategoryRepository categoryRepository;

    public UniqueCategoryNameValidator(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public boolean isValid(String name, ConstraintValidatorContext context) {
        if(name == null || name.isBlank()) {
            return true; // Allow null or blank names
        }
        return !categoryRepository.existsByNameIgnoreCase(name.trim());
    }

}
