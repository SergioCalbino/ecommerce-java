package com.example.demo.Dto.order;

import com.example.demo.Dto.orderItem.OrderItemResponseDto;
import com.example.demo.Dto.customer.CustomerResponseDto;
import com.example.demo.entities.utilities.OrderState;

import java.util.Date;
import java.util.List;

public class OrderResponseDto {

    public OrderResponseDto() {
    }

    public OrderResponseDto(Date date, CustomerResponseDto customerResponseDto, List<OrderItemResponseDto> orderItemResponseDto, Double total, OrderState state) {
        this.date = date;
        this.customerResponseDto = customerResponseDto;
        this.orderItemResponseDto = orderItemResponseDto;
        this.total = total;
        this.state = state;
    }

    private Long id;
    private Date date;
    private CustomerResponseDto customerResponseDto;
    private List<OrderItemResponseDto> orderItemResponseDto;
    private Double total;
    private OrderState state;
    private Long customerId;

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public CustomerResponseDto getCustomerResponseDto() {
        return customerResponseDto;
    }

    public void setCustomerResponseDto(CustomerResponseDto customerResponseDto) {
        this.customerResponseDto = customerResponseDto;
    }

    public List<OrderItemResponseDto> getOrderItemResponseDto() {
        return orderItemResponseDto;
    }

    public void setOrderItemResponseDto(List<OrderItemResponseDto> orderItemResponseDto) {
        this.orderItemResponseDto = orderItemResponseDto;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public OrderState getState() {
        return state;
    }

    public void setState(OrderState state) {
        this.state = state;
    }
}
