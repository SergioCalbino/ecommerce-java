package com.example.demo.Dto;

public class OrderItemResponseDto {

    public OrderItemResponseDto() {
    }

    public OrderItemResponseDto(ProductResponseDto productResponseDto, OrderResponseDto orderResponseDto, Double unitPrice, Integer quantity) {
        this.productResponseDto = productResponseDto;
        this.orderResponseDto = orderResponseDto;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
    }

    private Long id;
    private ProductResponseDto productResponseDto;
    private OrderResponseDto orderResponseDto;
    private Double unitPrice;
    private Integer quantity;

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

    public OrderResponseDto getOrderResponseDto() {
        return orderResponseDto;
    }

    public void setOrderResponseDto(OrderResponseDto orderResponseDto) {
        this.orderResponseDto = orderResponseDto;
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
