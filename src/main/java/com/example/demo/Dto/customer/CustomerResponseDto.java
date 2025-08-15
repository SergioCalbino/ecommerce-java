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
    private String telephone;


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

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
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
