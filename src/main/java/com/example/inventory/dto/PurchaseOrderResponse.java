package com.example.inventory.dto;

import  com.example.inventory.entity.PurchaseOrder;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class PurchaseOrderResponse {

    private Long id;
    private String purchaseOrderNumber;
    private Long supplierId;
    private String supplierName;
    private LocalDate orderDate;
    private String notes;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public PurchaseOrderResponse(PurchaseOrder purchaseOrder) {
        this.id = purchaseOrder.getId();
        this.purchaseOrderNumber = purchaseOrder.getPurchaseOrderNumber();
        this.supplierId = purchaseOrder.getSupplier().getId();
        this.supplierName = purchaseOrder.getSupplier().getName();
        this.orderDate = purchaseOrder.getOrderDate();
        this.notes = purchaseOrder.getNotes();
        this.createdAt = purchaseOrder.getCreatedAt();
        this.updatedAt = purchaseOrder.getUpdatedAt();
    }

    public Long getId() {
        return id;
    }

    public String getPurchaseOrderNumber() {
        return purchaseOrderNumber;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public String getNotes() {
        return notes;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
