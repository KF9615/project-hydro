package com.example.inventory.dto;

import com.example.inventory.entity.PurchaseOrderItem;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PurchaseOrderItemResponse {

    private Long id;
    private Long purchaseOrderId;
    private String purchaseOrderNumber;
    private Long productId;
    private String productName;
    private int quantityOrdered;
    private BigDecimal unitCost;
    private BigDecimal lineTotal;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public PurchaseOrderItemResponse(PurchaseOrderItem purchaseOrderItem) {

        this.id = purchaseOrderItem.getId();

        this.purchaseOrderId = purchaseOrderItem.getPurchaseOrder().getId();

        this.purchaseOrderNumber = purchaseOrderItem.getPurchaseOrder().getPurchaseOrderNumber();

        this.productId = purchaseOrderItem.getProduct().getId();

        this.productName = purchaseOrderItem.getProduct().getName();

        this.quantityOrdered = purchaseOrderItem.getQuantityOrdered();

        this.unitCost = purchaseOrderItem.getUnitCost();

        this.lineTotal = purchaseOrderItem.getUnitCost().multiply(BigDecimal.valueOf(purchaseOrderItem.getQuantityOrdered()));

        this.createdAt = purchaseOrderItem.getCreatedAt();
        this.updatedAt = purchaseOrderItem.getUpdatedAt();
    }

    public Long getId() {
        return id;
    }

    public Long getPurchaseOrderId() {
        return purchaseOrderId;
    }

    public String getPurchaseOrderNumber() {
        return purchaseOrderNumber;
    }

    public Long getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public int getQuantityOrdered() {
        return quantityOrdered;
    }

    public BigDecimal getUnitCost() {
        return unitCost;
    }

    public BigDecimal getLineTotal() {
        return lineTotal;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

}
