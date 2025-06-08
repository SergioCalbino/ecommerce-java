package com.example.demo.Dto.cartItem;

public class RemoveItemDto {

    private String customerEmail;
    private Long productId;
    private Integer quantity;

    public RemoveItemDto() {
    }

    public RemoveItemDto(String customerEmail, Long productId, Integer quantity) {
        this.customerEmail = customerEmail;
        this.productId = productId;
        this.quantity = quantity;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
