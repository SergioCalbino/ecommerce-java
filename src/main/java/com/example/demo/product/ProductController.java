package com.example.demo.product;

import com.example.demo.entities.Dto.ProductDto;
import com.example.demo.entities.Dto.ProductMapper;
import com.example.demo.entities.Dto.ProductResponseDto;
import com.example.demo.entities.Product;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {


    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("")
    public List<Product> list() {
        return productService.findAll();
    }

    @PostMapping("")
    public ResponseEntity<?> create (@Valid @RequestBody ProductDto productDto){
        Product productNew = productService.save(productDto);
        ProductResponseDto responseDto = ProductMapper.toDto(productNew);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> listById (@PathVariable Long id) {
        Optional<Product> product = productService.findById(id);
        if (product.isPresent()) {
            return ResponseEntity.ok(product.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Product product) {
        Optional<Product> productDb = productService.findById(id);
        if (productDb.isPresent()) {
            productService.update(id, product);
            return ResponseEntity.ok(productDb.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<Product> productDb = productService.findById(id);
        if (productDb.isPresent()) {
            productService.delete(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }


}
