package com.example.inventory.repository;

import com.example.inventory.entity.PurchaseOrder;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Long> {

    boolean existsByPurchaseOrderNumberIgnoreCase(String purchaseOrderNumber);


    @Override
    @EntityGraph(attributePaths = "supplier")
    List<PurchaseOrder> findAll();

    @Override
    @EntityGraph(attributePaths = "supplier")
    Optional<PurchaseOrder> findById(Long id);
}
