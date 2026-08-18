package com.example.inventory.repository;

import com.example.inventory.entity.Product;
import jakarta.persistence.Entity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    boolean existsBySkuIgnoreCase(String sku);

    @Override
    @EntityGraph(attributePaths = "category") // when fetching Products, also fetch Product's Category
    List<Product> findAll();

    @Override
    @EntityGraph(attributePaths = "category")
    Optional<Product> findById(Long id);
}
