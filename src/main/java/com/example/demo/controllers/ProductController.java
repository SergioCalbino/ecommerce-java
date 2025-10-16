package com.example.demo.controllers;

import com.example.demo.Dto.product.ProductUpdateDto;
import com.example.demo.herlpers.ApiResponse;
import com.example.demo.Dto.product.ProductDto;
import com.example.demo.Dto.product.ProductResponseDto;
import com.example.demo.interfaces.ProductService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/products")
public class ProductController {


    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/admin")
    public ResponseEntity<?> list(
            @RequestParam(name = "name", required = false) String name,
            @PageableDefault(page = 0, size = 5, sort = "name", direction = Sort.Direction.ASC)Pageable pageable) {
        Page<ProductResponseDto> productResponseDtoList = productService.findAll(name, pageable);
        if (productResponseDtoList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(productResponseDtoList);
    }

    @GetMapping("/customer")
    public ResponseEntity<?> listCustomer(
            @RequestParam(name = "name", required = false) String name,
            @PageableDefault(page = 0, size = 5, sort = "name", direction = Sort.Direction.ASC)Pageable pageable) {
        Page<ProductResponseDto> productResponseDtoList = productService.findAllActive(name, pageable);
        if (productResponseDtoList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(productResponseDtoList);
    }

    @PostMapping("")
    public ResponseEntity<?> create (@Valid @RequestBody ProductDto productDto){
        ProductResponseDto productResponseDto = productService.save(productDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>(
                201,
                "Product created successfully",
                productResponseDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> listById (@PathVariable Long id) {
        ProductResponseDto ProductResponseDto = productService.findById(id);
        return ResponseEntity.ok(ProductResponseDto);


    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update( @PathVariable Long id, @Valid @RequestBody ProductUpdateDto productDto) {
        ProductResponseDto productResponseDto = productService.update(id, productDto);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(
                200,
                "Product updated successfully",
                productResponseDto));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        productService.delete(id);
        return ResponseEntity.noContent().build();

    }

    // Hacer un controler quye permita reactivar los propductos

   /* @GetMapping("/search")
    public ResponseEntity<?> searchProduct(@RequestParam String name){
        ProductResponseDto productToSearch = productService.findByName(name);
        return ResponseEntity.ok(new ApiResponse<>(
                200,
                "Product was found",
                productToSearch

        ));
    } */


}
