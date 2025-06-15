package com.example.demo.Dto.order;

import com.example.demo.Dto.customer.CustomerDto;

public class CreateOrderDto {

    private CustomerDto customerDto;
    private OrderDto orderDto;

    public CreateOrderDto(CustomerDto customerDto, OrderDto orderDto) {
        this.customerDto = customerDto;
        this.orderDto = orderDto;
    }

    public CreateOrderDto() {
    }

    public CustomerDto getCustomerDto() {
        return customerDto;
    }

    public void setCustomerDto(CustomerDto customerDto) {
        this.customerDto = customerDto;
    }

    public OrderDto getOrderDto() {
        return orderDto;
    }

    public void setOrderDto(OrderDto orderDto) {
        this.orderDto = orderDto;
    }
}
