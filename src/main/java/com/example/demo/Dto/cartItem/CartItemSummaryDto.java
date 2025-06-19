package com.example.demo.Dto.cartItem;

import java.math.BigDecimal;

public class CartItemSummaryDto {

    private Long productId;
    private String name;
    private Integer quantity;
    private Integer stock;
    private BigDecimal price;

    public CartItemSummaryDto() {
    }

    public CartItemSummaryDto(Long productId, String name, Integer quantity, Integer stock, BigDecimal price) {
        this.productId = productId;
        this.name = name;
        this.quantity = quantity;
        this.stock = stock;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}
