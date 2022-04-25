package com.example.demo.repository;

import com.example.demo.persistance.product.Product;
import com.example.demo.persistance.product.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {

    List<Product> findByTypeOrPriceBetween(ProductType type, Double min, Double max);
}
