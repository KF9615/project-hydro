package com.example.inventory.dto;

import com.example.inventory.entity.Product;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ProductResponse {

    private final Long id;
    private final String sku;
    private final String name;
    private final String description;
    private final BigDecimal unitPrice;
    private final int reorderLevel;
    private final Long categoryId;
    private final String categoryName;
    private final Boolean active;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public ProductResponse(Product product) {
        this.id = product.getId();
        this.sku = product.getSku();
        this.name = product.getName();
        this.description = product.getDescription();
        this.unitPrice = product.getUnitPrice();
        this.reorderLevel = product.getReorderLevel();
        this.categoryId = product.getCategory().getId();
        this.categoryName = product.getCategory().getName();
        this.active = product.isActive();
        this.createdAt = product.getCreatedAt();
        this.updatedAt = product.getUpdatedAt();
    }

    public Long getId() {
        return id;
    }

    public String getSku() {
        return sku;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public int getReorderLevel() {
        return reorderLevel;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public Boolean getActive() {
        return active;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
