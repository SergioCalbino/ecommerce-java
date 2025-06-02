package com.example.demo.Dto;

import com.example.demo.entities.ShoppingCart;

import java.util.List;

public class CartItemResponseDto {

    public CartItemResponseDto() {
    }

    public CartItemResponseDto(Long id, ProductResponseDto product, Integer quantity, ShoppingCartResponseDto shoppingCartResponseDto) {
        this.id = id;
        this.product = product;
        this.quantity = quantity;
        this.shoppingCartResponseDto = shoppingCartResponseDto;
    }

    private Long id;
    private ProductResponseDto product;
    private Integer quantity;
    private ShoppingCartResponseDto shoppingCartResponseDto;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProductResponseDto getProduct() {
        return product;
    }

    public void setProduct(ProductResponseDto product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public ShoppingCartResponseDto getShoppingCartResponseDto() {
        return shoppingCartResponseDto;
    }

    public void setShoppingCartResponseDto(ShoppingCartResponseDto shoppingCartResponseDto) {
        this.shoppingCartResponseDto = shoppingCartResponseDto;
    }
}
