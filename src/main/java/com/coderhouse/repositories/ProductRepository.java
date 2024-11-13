package com.coderhouse.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coderhouse.models.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
