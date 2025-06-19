package com.example.demo.validations;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UniqueProductNameValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueProductName {
    String message() default "Product name must be unique";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
