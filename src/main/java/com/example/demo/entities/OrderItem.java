package com.example.demo.entities;


import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "order_items")
public class OrderItem {

    public OrderItem() {
    }

    public OrderItem(Product product, Order order, BigDecimal unitPrice, Integer quantity, BigDecimal total) {
        this.product = product;
        this.order = order;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.total = total;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    private BigDecimal unitPrice;

    private Integer quantity;

    private BigDecimal total;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    // Metodo para caclcular el subtotal de cada orderItem
    public BigDecimal getSubtotal() {
        BigDecimal quantityDecimal = BigDecimal.valueOf(quantity);
        return unitPrice.multiply(quantityDecimal);
    }

    public void setSubtotal(BigDecimal total) {
        this.total = total;

    }
}
