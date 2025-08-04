package com.example.demo.Dto.auth;

import com.example.demo.Dto.customer.CustomerDto;
import com.example.demo.Dto.customer.CustomerResponseDto;
import com.example.demo.entities.Customer;

public class LoginResponseDto {

    private String accessToken;
    private String refreshToken;
    private CustomerResponseDto customer;

    public LoginResponseDto(String accessToken, String refreshToken, CustomerResponseDto customer) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.customer = customer;
    }

    public CustomerResponseDto getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerResponseDto customer) {
        this.customer = customer;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
