package com.example.inventory.repository;

import com.example.inventory.entity.Supplier;
import com.example.inventory.repository.SupplierRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository <Supplier, Long> {
    boolean existsBySupplierCodeIgnoreCase(String supplierCode);
}
