package com.example.demo.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "cart_items")
public class CartItem {

    public CartItem() {
    }

    public CartItem(Product product, Integer quantity, ShoppingCart shoppingCart) {
        this.product = product;
        this.quantity = quantity;
        this.shoppingCart = shoppingCart;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Cada ítem refiere a un producto
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;


    // Cada ítem pertenece a un carrito
    @ManyToOne
    @JoinColumn(name = "shopping_cart_id", nullable = false)
    private ShoppingCart shoppingCart;

    private Integer quantity;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }
}
