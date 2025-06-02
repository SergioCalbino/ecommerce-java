package com.example.demo.services;


import com.example.demo.Dto.CustomerDto;
import com.example.demo.Dto.CustomerResponseDto;

public interface CustomerService {

    CustomerResponseDto create(CustomerDto customerDto);
}
