package com.example.demo.category;

import com.example.demo.entities.Category;
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
    public ResponseEntity<List<Category>> list() {
        List<Category> categories = categoryService.listAll();
        return ResponseEntity.ok(categories);
    }

    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody Category category) {
        Category categoryNew = categoryService.save(category);
        return ResponseEntity.ok(categoryNew);

    }


}
