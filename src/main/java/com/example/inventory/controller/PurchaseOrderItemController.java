package com.example.inventory.controller;


import com.example.inventory.dto.PurchaseOrderItemRequest;
import com.example.inventory.dto.PurchaseOrderItemResponse;
import com.example.inventory.entity.PurchaseOrderItem;
import com.example.inventory.service.PurchaseOrderItemService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/purchase-order-items")
public class PurchaseOrderItemController {

    private final PurchaseOrderItemService purchaseOrderItemService;

    public PurchaseOrderItemController(PurchaseOrderItemService purchaseOrderItemService) {
        this.purchaseOrderItemService = purchaseOrderItemService;
    }

    @GetMapping
    public List<PurchaseOrderItemResponse> getPurchaseOrderItems() {

        return  purchaseOrderItemService.getAllPurchaseOrderItems();
    }

    @GetMapping("/purchase-order/{purchaseOrderId}")
    public List<PurchaseOrderItemResponse> getPurchaseOrderItemResponse(@PathVariable Long purchaseOrderId) {

        return purchaseOrderItemService.getItemsByPurchaseOrder(purchaseOrderId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PurchaseOrderItemResponse createPurchaseOrderItem(@Valid @RequestBody PurchaseOrderItemRequest request) {

        return purchaseOrderItemService.createPurchaseOrderItem(request);
    }
}
