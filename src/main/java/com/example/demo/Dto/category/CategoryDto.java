package com.example.demo.Dto.category;

import com.example.demo.Dto.product.ProductDto;
import com.example.demo.validations.UniqueCategoryName;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategoryDto {

    private Long id;

    @NotBlank
    @Size(min = 4, max = 20)
    @UniqueCategoryName(message = "Category name already exists")
    private String name;

    private List<ProductDto> products;

    public CategoryDto() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<ProductDto> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDto> products) {
        this.products = products;
    }

    public CategoryDto(Long id, String name, List<ProductDto> products) {
        this.id = id;
        this.name = name;
        this.products = products;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ProductDto> getProductDtos() {
        return products;
    }

    public void setProductDtos(List<ProductDto> products) {
        this.products = products;
    }
}
