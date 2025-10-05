package com.example.demo.Dto.product;

import com.example.demo.validations.UniqueProductName;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public class ProductDto {
    private Long id;

    @NotBlank
    @Size(min = 4, max = 40)
    @UniqueProductName(message = "Product name already exists")
    private String name;

    @NotNull
    @Min(value = 1)
    private BigDecimal price;

    @NotBlank
    @Size(min = 4, max = 100)
    private String description;

    private String image;

    @NotNull
    @Min(value = 1)
    private Integer stock;


    private Long categoryId;

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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}
