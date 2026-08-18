package com.example.inventory.repository;


import com.example.inventory.entity.PurchaseOrderItem;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PurchaseOrderItemRepository extends JpaRepository<PurchaseOrderItem, Long> {

    @Override
    @EntityGraph(attributePaths = {"purchaseOrder", "product"})
    List<PurchaseOrderItem> findAll();

    @Override
    @EntityGraph(attributePaths = {"purchaseOrder", "product"})
    Optional<PurchaseOrderItem> findById(Long id);

    @EntityGraph(attributePaths = {"purchaseOrder", "product"})
    List<PurchaseOrderItem> findByPurchaseOrderId(Long purchaseOrderId); // search by id
}
