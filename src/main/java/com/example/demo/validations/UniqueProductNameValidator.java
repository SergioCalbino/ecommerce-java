package com.example.demo.validations;

import com.example.demo.repositories.ProductRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

@Component
public class UniqueProductNameValidator implements ConstraintValidator<UniqueProductName, String> {

    private final ProductRepository productRepository;

    public UniqueProductNameValidator(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @Override
    public boolean isValid(String name, ConstraintValidatorContext context) {
        if (name == null || name.isBlank()) {
            return true;
        }
        return !productRepository.existsByNameIgnoreCase(name.trim());
    }

}
