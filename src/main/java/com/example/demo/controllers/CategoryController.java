package com.example.demo.controllers;

import com.example.demo.services.CategoryService;
import com.example.demo.Dto.category.CategoryDto;
import com.example.demo.Dto.category.CategoryResponseDto;
import com.example.demo.herlpers.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("")
    public ResponseEntity<List<CategoryResponseDto>> list() {
        List<CategoryResponseDto> categoriesResponseDto = categoryService.listAll();
        return ResponseEntity.status(HttpStatus.OK).body(categoriesResponseDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> listById(@PathVariable  Long id) {
        CategoryResponseDto categoryResponseDto = categoryService.findById(id);
        return ResponseEntity.ok(categoryResponseDto);
    }

    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody @Valid CategoryDto categoryDto) {
        CategoryResponseDto categoryResponseDto = categoryService.save(categoryDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>(
                201,
                "Category created successfully",
                categoryResponseDto
        ));


    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody CategoryDto categoryDto) {
        CategoryResponseDto categoryResponseDto = categoryService.update(id, categoryDto);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(
                200,
                "Category updated successfully",
                categoryResponseDto
        ));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        categoryService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ApiResponse<>(
                204,
                "Category deleted successfully",
                null
        ));
    }

    @GetMapping("/search")
    public ResponseEntity<?> findByName(@RequestParam String name) {
        CategoryResponseDto categoryResponseDto = categoryService.findByName(name);
        return ResponseEntity.ok(new ApiResponse<>(
                200,
                "Category found",
                categoryResponseDto
        ));
    }


}
