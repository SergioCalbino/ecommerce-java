package com.example.demo.Dto.cartItem;

import com.example.demo.Dto.product.ProductResponseDto;
import com.example.demo.Dto.shoppingCart.ShoppingCartResponseDto;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.math.BigDecimal;

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
    @JsonIgnore
    private ShoppingCartResponseDto shoppingCartResponseDto;
    @JsonIgnore
    private Integer shoppingCartId;

    private BigDecimal subTotal;

    public Integer getShoppingCartId() {
        return shoppingCartId;
    }

    public void setShoppingCartId(Integer shoppingCartId) {
        this.shoppingCartId = shoppingCartId;
    }

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

    public BigDecimal getSubTotal() {
        return product.getPrice().multiply(BigDecimal.valueOf(quantity));
    }

    public void setSubTotal(BigDecimal subTotal) {
        this.subTotal = subTotal;

    }
}
