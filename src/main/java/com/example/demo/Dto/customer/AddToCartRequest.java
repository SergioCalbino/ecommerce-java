package com.example.demo.Dto.customer;

import com.example.demo.Dto.product.ProductDto;

public class AddToCartRequest {
    private CustomerDto customerDto;
    private ProductDto productDto;
    private Integer quantity;

    public CustomerDto getCustomerDto() {
        return customerDto;
    }

    public void setCustomerDto(CustomerDto customerDto) {
        this.customerDto = customerDto;
    }

    public ProductDto getProductDto() {
        return productDto;
    }

    public void setProductDto(ProductDto productDto) {
        this.productDto = productDto;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
