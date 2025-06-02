package com.example.demo.Dto;


import com.example.demo.entities.ShoppingCart;

import java.util.List;

public class CartItemDto {


    public CartItemDto(){}


    public CartItemDto(Long id, ProductDto product, Integer quantity, ShoppingCart shoppingCart) {
        this.id = id;
        this.product = product;
        this.quantity = quantity;
        this.shoppingCart = shoppingCart;
    }

    private Long id;
    private ProductDto product;
    private Integer quantity;
    private ShoppingCart shoppingCart;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProductDto getProduct() {
        return product;
    }

    public void setProduct(ProductDto product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }
}
