package com.example.demo.Dto.orderItem;

import com.example.demo.Dto.order.OrderResponseDto;
import com.example.demo.Dto.product.ProductResponseDto;

import java.math.BigDecimal;

public class OrderItemResponseDto {

    public OrderItemResponseDto() {
    }

    public OrderItemResponseDto(ProductResponseDto productResponseDto, Long orderId, BigDecimal unitPrice, Integer quantity, BigDecimal total) {
        this.productResponseDto = productResponseDto;
        this.orderId = orderId;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
    }

    private Long id;
    private ProductResponseDto productResponseDto;
    private Long orderId;
    private BigDecimal unitPrice;
    private Integer quantity;
    private BigDecimal total;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProductResponseDto getProductResponseDto() {
        return productResponseDto;
    }

    public void setProductResponseDto(ProductResponseDto productResponseDto) {
        this.productResponseDto = productResponseDto;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }


    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getSubtotal() {
        return unitPrice.multiply(BigDecimal.valueOf(quantity));
    }

    public void setSubtotal(BigDecimal total) {
        this.total = total;
    }

}
