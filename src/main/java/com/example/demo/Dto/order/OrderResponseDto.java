package com.example.demo.Dto.order;

import com.example.demo.Dto.customer.CustomerSummaryDto;
import com.example.demo.Dto.orderItem.OrderItemResponseDto;
import com.example.demo.Dto.customer.CustomerResponseDto;
import com.example.demo.entities.utilities.OrderState;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class OrderResponseDto {


    public OrderResponseDto() {
    }

    public OrderResponseDto(Date date, CustomerResponseDto customerResponseDto, List<OrderItemResponseDto> orderItemResponseDto, BigDecimal total, OrderState state) {
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
    private BigDecimal total;
    private OrderState state;
    private Long customerId;
    private CustomerSummaryDto customerSummaryDto;

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

    public CustomerSummaryDto getCustomerResponseDto() {
        return customerSummaryDto;
    }

    public void setCustomerResponseDto(CustomerSummaryDto customerSummaryDto) {
        this.customerSummaryDto = customerSummaryDto;
    }

    public List<OrderItemResponseDto> getOrderItemResponseDto() {
        return orderItemResponseDto;
    }

    public void setOrderItemResponseDto(List<OrderItemResponseDto> orderItemResponseDto) {
        this.orderItemResponseDto = orderItemResponseDto;
    }

    public BigDecimal getTotal() {
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
