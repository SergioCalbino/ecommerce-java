package com.example.demo.interfaces;


import com.example.demo.Dto.customer.CustomerDto;
import com.example.demo.Dto.customer.CustomerResponseDto;

public interface CustomerService {

    CustomerResponseDto create(CustomerDto customerDto);

}
