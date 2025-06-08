package com.example.demo.Dto.customer;

import com.example.demo.Dto.order.OrderDto;
import com.example.demo.Dto.shoppingCart.ShoppingCartDto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

public class CustomerDto {

    public CustomerDto(){}

    public CustomerDto(String name, String email, String password, String address, List<OrderDto> orderDto, ShoppingCartDto shoppingCartDto) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.address = address;
        this.orderDto = orderDto;
        this.shoppingCartDto = shoppingCartDto;
    }


    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Size(min = 8, message = "Password must be at least 8 characters")
    private String password;

    @NotBlank
    private String address;

    private List<OrderDto> orderDto;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private ShoppingCartDto shoppingCartDto;

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

    public void setAddress(String address) {
        this.address = address;
    }

    public List<OrderDto> getOrderDto() {
        return orderDto;
    }

    public void setOrderDto(List<OrderDto> orderDto) {
        this.orderDto = orderDto;
    }

    public ShoppingCartDto getShoppingCartDto() {
        return shoppingCartDto;
    }

    public void setShoppingCartDto(ShoppingCartDto shoppingCartDto) {
        this.shoppingCartDto = shoppingCartDto;
    }
}
