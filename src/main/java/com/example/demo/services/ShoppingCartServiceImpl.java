package com.example.demo.services;

import com.example.demo.Dto.cartItem.RemoveItemDto;
import com.example.demo.Dto.customer.CustomerCartDto;
import com.example.demo.Dto.customer.CustomerDto;
import com.example.demo.Dto.customer.CustomerResponseDto;
import com.example.demo.Dto.product.ProductDto;
import com.example.demo.Dto.shoppingCart.ShoppingCartResponseDto;
import com.example.demo.entities.CartItem;
import com.example.demo.entities.Customer;
import com.example.demo.entities.Product;
import com.example.demo.entities.ShoppingCart;
import com.example.demo.exceptions.InsufficientStockException;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.mappers.CustomerMapper;
import com.example.demo.mappers.ShoppingMapper;
import com.example.demo.repositories.CustomerRepository;
import com.example.demo.repositories.ProductRepository;
import com.example.demo.utils.UpdateTotal;
import org.hibernate.sql.Update;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService{


    private final CustomerRepository customerRepository;
    private  final ProductRepository productRepository;

    public ShoppingCartServiceImpl(CustomerRepository customerRepository, ProductRepository productRepository) {
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
    }

    @Override
    public ShoppingCartResponseDto addToShoppingCart(CustomerDto customerDto, ProductDto productDto, Integer quantity) {

        if (productDto.getId() == null) {
            throw new IllegalArgumentException("Id Product can´t be null");
        }

        Customer customer = customerRepository.findByEmail(customerDto.getEmail())
                .orElseThrow(() -> new NotFoundException("Customer with email " + customerDto.getEmail() + "not found"));

        Product product = productRepository.findById(productDto.getId())
                .orElseThrow(() -> new NotFoundException("Product + " + productDto.getName()+ " not found"));

        ShoppingCart shoppingCart = customer.getShoppingCart();
        if (shoppingCart == null) {
            throw new IllegalStateException("Cart cant be null");
        }

        if (product.getStock() < quantity) {
            throw new InsufficientStockException("Not enough stock for product " + product.getName());
        }

        boolean found = false;

        for (CartItem item: shoppingCart.getCartItems()) {
            if (item.getProduct().getId().equals(product.getId())){
                item.setQuantity(item.getQuantity() + quantity);
                shoppingCart.setTotal(shoppingCart.getTotal());
                item.getProduct().setStock(item.getProduct().getStock() - quantity);
                found = true;
                break;
            }
        }

        if (!found) {
            CartItem cartItem = new CartItem(product, quantity, shoppingCart);

            shoppingCart.getCartItems().add(cartItem);
            shoppingCart.setTotal(shoppingCart.getTotal());
            product.setStock(product.getStock() - quantity);
        }


        System.out.println("CART ITEMS:");
        for (CartItem item : shoppingCart.getCartItems()) {
            System.out.println("- " + item.getProduct().getName() + " x" + item.getQuantity());
        }

        customer.setShoppingCart(shoppingCart);
        customerRepository.save(customer);

        //return CustomerMapper.toDto(customer);
        return ShoppingMapper.toDto(shoppingCart);

    }

    @Override
    public CustomerCartDto removeFromShoppingCart(RemoveItemDto removeItemDto) {

        Customer customer = customerRepository.findByEmail(removeItemDto.getCustomerEmail())
                .orElseThrow(() -> new NotFoundException("Customer " + removeItemDto.getCustomerEmail() + "not found"));

        Product product = productRepository.findById(removeItemDto.getProductId())
                .orElseThrow(() -> new NotFoundException("Product " + removeItemDto.getProductId() + "not found"));

        ShoppingCart shoppingCart = customer.getShoppingCart();

        CartItem cartItem = shoppingCart.getCartItems().stream()
                .filter(i -> i.getProduct().getId().equals(product.getId()))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Product not found"));


        if (cartItem.getQuantity() > removeItemDto.getQuantity()) {
            cartItem.setQuantity(cartItem.getQuantity() - removeItemDto.getQuantity());


        } else {
            shoppingCart.getCartItems().remove(cartItem);
        }
        product.setStock(product.getStock() + removeItemDto.getQuantity());

        //Muestra el total del carrito actualizado
        UpdateTotal.updateTotal(shoppingCart);


        customerRepository.save(customer);

        return CustomerMapper.toCleanCartResponse(customer);

    }
}
