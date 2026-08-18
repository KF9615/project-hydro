package com.example.inventory.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public class PurchaseOrderItemRequest {

    @NotNull(message = "Purchase order ID is required")
    private Long purchaseOrderId;

    @NotNull(message = "Product ID is required")
    private Long productId;

    @Positive(message = "Ordered quanity must be greater than zero")
    private int quantityOrdered;

    @NotNull(message = "Unit cost is required")
    @DecimalMin(value = "0.00",inclusive = true)
    private BigDecimal unitCost;

    public Long getPurchaseOrderId() {
        return purchaseOrderId;
    }

    public void setPurchaseOrderId(Long purchaseOrderId) {
        this.purchaseOrderId = purchaseOrderId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public int getQuantityOrdered() {
        return quantityOrdered;
    }

    public void setQuantityOrdered(int quantityOrdered) {
        this.quantityOrdered = quantityOrdered;
    }

    public BigDecimal getUnitCost() {
        return unitCost;
    }

    public void setUnitCost(BigDecimal unitCost) {
        this.unitCost = unitCost;
    }
}
