package com.example.demo.Dto.cartItem;


import com.example.demo.Dto.product.ProductDto;
import com.example.demo.Dto.shoppingCart.ShoppingCartDto;

public class CartItemDto {


    public CartItemDto(){}


    public CartItemDto(ProductDto product, Integer quantity, ShoppingCartDto shoppingCartDto) {

        this.product = product;
        this.quantity = quantity;
        this.shoppingCartDto = shoppingCartDto;
    }

    private Long id;
    private ProductDto product;
    private Integer quantity;
    private ShoppingCartDto shoppingCartDto;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProductDto getProduct() {
        return product;
    }

    public void setProduct(ProductDto product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public ShoppingCartDto getShoppingCartDto() {
        return shoppingCartDto;
    }

    public void setShoppingCartDto(ShoppingCartDto shoppingCartDto) {
        this.shoppingCartDto = shoppingCartDto;
    }
}
