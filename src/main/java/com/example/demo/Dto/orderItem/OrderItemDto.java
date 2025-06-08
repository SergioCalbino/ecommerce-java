package com.example.demo.Dto.orderItem;

import com.example.demo.Dto.order.OrderDto;
import com.example.demo.Dto.product.ProductDto;

public class OrderItemDto {

    public OrderItemDto() {
    }

    public OrderItemDto(ProductDto productDto, OrderDto orderDto, Double unitPrice, Integer quantity) {
        this.productDto = productDto;
        this.orderDto = orderDto;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
    }

    private Long id;
    private ProductDto productDto;
    private OrderDto orderDto;
    private Double unitPrice;
    private Integer quantity;

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

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
