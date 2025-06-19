package com.example.demo.Dto.order;

import com.example.demo.Dto.cartItem.CartItemDto;
import com.example.demo.Dto.orderItem.OrderItemDto;
import com.example.demo.Dto.customer.CustomerDto;
import com.example.demo.entities.utilities.OrderState;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderDto {

    public OrderDto() {
    }

    public OrderDto(Date date, CustomerDto customerDto, List<OrderItemDto> orderItemDto, BigDecimal total, OrderState state) {
        this.date = date;
        this.customerDto = customerDto;
        this.orderItemDto = orderItemDto;
        this.total = total;
        this.state = state;
    }

    private Long id;
    private Date date;
    private CustomerDto customerDto;
    private List<OrderItemDto> orderItemDto = new ArrayList<>();
    private BigDecimal total;
    private OrderState state;

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

    public CustomerDto getCustomerDto() {
        return customerDto;
    }

    public void setCustomerDto(CustomerDto customerDto) {
        this.customerDto = customerDto;
    }

    public List<OrderItemDto> getOrderItemDto() {
        return orderItemDto;
    }

    public void setOrderItemDtos(List<OrderItemDto> orderItemDto) {
        this.orderItemDto = orderItemDto;
    }

    public BigDecimal getTotal() {
        BigDecimal total = BigDecimal.ZERO;
        for (OrderItemDto item : this.orderItemDto) {
            total = total.add(item.getSubtotal());
        }
        return total;


    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public OrderState getState() {
        return state;
    }

    public void setState(OrderState state) {
        this.state = state;
    }
}
