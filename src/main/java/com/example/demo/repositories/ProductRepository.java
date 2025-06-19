package com.example.demo.repositories;

import com.example.demo.entities.Product;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    boolean existsByNameIgnoreCase(String name);
    Optional<Product> findByNameIgnoreCase (String name);

}
