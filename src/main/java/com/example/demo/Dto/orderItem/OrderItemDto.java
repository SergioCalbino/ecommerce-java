package com.example.demo.Dto.orderItem;

import com.example.demo.Dto.order.OrderDto;
import com.example.demo.Dto.product.ProductDto;

import java.math.BigDecimal;

public class OrderItemDto {

    public OrderItemDto() {
    }

    public OrderItemDto(ProductDto productDto, OrderDto orderDto, BigDecimal unitPrice, Integer quantity, BigDecimal total) {
        this.productDto = productDto;
        this.orderDto = orderDto;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.total = total;
    }

    private Long id;
    private ProductDto productDto;
    private OrderDto orderDto;
    private BigDecimal unitPrice;
    private Integer quantity;
    private BigDecimal total;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProductDto getProductDto() {
        return productDto;
    }

    public void setProductDto(ProductDto productDto) {
        this.productDto = productDto;
    }

    public OrderDto getOrderDto() {
        return orderDto;
    }

    public void setOrderDto(OrderDto orderDto) {
        this.orderDto = orderDto;
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
