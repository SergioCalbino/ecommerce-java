package com.example.demo.Dto.auth;

import com.example.demo.entities.Customer;

public class AuthResponseDto {

    private String token;
    private Customer customer;

    public AuthResponseDto( String token, Customer customer) {
        this.token = token;
        this.customer = customer;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
