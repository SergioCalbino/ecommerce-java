package com.example.demo.Dto.customer;

import com.example.demo.Dto.order.OrderResponseDto;
import com.example.demo.Dto.shoppingCart.ShoppingCartResponseDto;

import java.util.List;

public class CustomerResponseDto {

    private Long id;
    private String name;
    private String email;
    private String password;
    private String address;

    private List<OrderResponseDto> orderResponseDto;
    private ShoppingCartResponseDto shoppingResponseDto;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddres(String address) {
        this.address = address;
    }

    public ShoppingCartResponseDto getShoppingResponseDto() {
        return shoppingResponseDto;
    }

    public void setShoppingResponseDto(ShoppingCartResponseDto shoppingResponseDto) {
        this.shoppingResponseDto = shoppingResponseDto;
    }

    public List<OrderResponseDto> getOrderResponseDto() {
        return orderResponseDto;
    }

    public void setOrderResponseDto(List<OrderResponseDto> orderResponseDto) {
        this.orderResponseDto = orderResponseDto;
    }
}
