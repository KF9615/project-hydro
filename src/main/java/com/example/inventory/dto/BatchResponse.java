package com.example.inventory.dto;

import com.example.inventory.entity.Batch;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class BatchResponse {

    private final Long id;
    private final String batchNumber;
    private final Long productId;
    private final String productName;
    private final LocalDate manufacturingDate;
    private final LocalDate expiryDate;
    private boolean active;
    private final LocalDateTime createdDate;
    private final LocalDateTime updatedDate;

    public BatchResponse(Batch batch) {
        this.id = batch.getId();
        this.batchNumber = batch.getBatchNumber();
        this.productId = batch.getProduct().getId();
        this.productName = batch.getProduct().getName();
        this.manufacturingDate = batch.getManufacturingDate();
        this.expiryDate = batch.getExpiryDate();
        this.active = batch.isActive();
        this.createdDate = batch.getCreatedAt();
        this.updatedDate = batch.getUpdatedAt();
    }

    public Long getId() {
        return id;
    }
    public String getBatchNumber() {
        return batchNumber;
    }

    public Long getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public LocalDate getManufacturingDate() {
        return manufacturingDate;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public boolean isActive() {
        return active;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public LocalDateTime getUpdatedDate() {
        return updatedDate;
    }
}
