package com.example.demo.controllers;


import com.example.demo.Dto.cartItem.RemoveItemDto;
import com.example.demo.Dto.customer.AddToCartRequest;
import com.example.demo.Dto.customer.CustomerCartDto;
import com.example.demo.Dto.customer.CustomerResponseDto;
import com.example.demo.herlpers.ApiResponse;
import com.example.demo.services.CustomerService;
import com.example.demo.services.ShoppingCartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shopping-cart")
public class ShoppingCartController {

    private final CustomerService customerService;
    private final ShoppingCartService shoppingCartService;

    public ShoppingCartController(CustomerService customerService, ShoppingCartService shoppingCartService) {
        this.customerService = customerService;
        this.shoppingCartService = shoppingCartService;
    }

    @PostMapping("/cart-item")
    public ResponseEntity<?> addToCart(@RequestBody AddToCartRequest request){
        CustomerResponseDto customerResponseDto = shoppingCartService.addToShoppingCart(
                request.getCustomerDto(),
                request.getProductDto(),
                request.getQuantity()
        );
        return ResponseEntity.ok(new ApiResponse<>(
                200,
                "Product add sucecesfully",
                customerResponseDto
        ));
    }

    @PutMapping("/cart-item")
    public ResponseEntity<?> removeToCart(@RequestBody RemoveItemDto cartItemRemoveRequestDto){
        CustomerCartDto customerResponseDto = shoppingCartService.removeFromShoppingCart(cartItemRemoveRequestDto);
        return ResponseEntity.ok(new ApiResponse<>(
                200,
                "Product remove successfully",
                customerResponseDto
        ));
    }

}
