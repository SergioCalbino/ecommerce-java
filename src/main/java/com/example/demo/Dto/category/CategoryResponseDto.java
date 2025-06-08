package com.example.demo.Dto.category;

import com.example.demo.Dto.product.ProductResponseDto;
import com.fasterxml.jackson.annotation.JsonInclude;


import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategoryResponseDto {

    private Long id;
    private String name;
    private List<ProductResponseDto> product;

    public CategoryResponseDto() {
    }

    public CategoryResponseDto(Long id, String name, List<ProductResponseDto> product) {
        this.id = id;
        this.name = name;
        this.product = product;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ProductResponseDto> getProductResponseDtoList() {
        return product;
    }

    public void setProductResponseDtoList(List<ProductResponseDto> product) {
        this.product = product;
    }
}
