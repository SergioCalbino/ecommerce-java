package com.example.demo.controllers;

import com.example.demo.Dto.customer.CustomerDto;
import com.example.demo.Dto.customer.CustomerResponseDto;
import com.example.demo.herlpers.ApiResponse;
import com.example.demo.interfaces.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }


    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody CustomerDto customerDto){
        CustomerResponseDto customerResponseDto = customerService.create(customerDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>(
                200,
                "Customer created successfully",
                customerResponseDto
        ));

    }
}
