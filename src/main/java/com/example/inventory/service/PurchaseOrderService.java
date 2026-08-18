package com.example.inventory.service;


import com.example.inventory.dto.PurchaseOrderRequest;
import com.example.inventory.dto.PurchaseOrderResponse;
import com.example.inventory.entity.PurchaseOrder;
import com.example.inventory.entity.Supplier;
import com.example.inventory.repository.PurchaseOrderRepository;
import com.example.inventory.repository.SupplierRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class PurchaseOrderService {

    private final PurchaseOrderRepository purchaseOrderRepository;
    private final SupplierRepository supplierRepository;

    public PurchaseOrderService(PurchaseOrderRepository purchaseOrderRepository, SupplierRepository supplierRepository) {
        this.purchaseOrderRepository = purchaseOrderRepository;
        this.supplierRepository = supplierRepository;
    }

    public List<PurchaseOrderResponse> getAllPurchaseOrders() {
        return purchaseOrderRepository.findAll()
                .stream()
                .map(PurchaseOrderResponse::new)
                .toList();
    }

    public PurchaseOrderResponse createPurchaseOrder(PurchaseOrderRequest request) {

        if (purchaseOrderRepository.existsByPurchaseOrderNumberIgnoreCase(
                request.getPurchaseOrderNumber())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "PurchaseOrderNumber already exists"
            );
        }

        Supplier supplier = supplierRepository.findById(request.getSupplierId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Supplier not found"));

        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.setPurchaseOrderNumber(request.getPurchaseOrderNumber());
        purchaseOrder.setSupplier(supplier);
        purchaseOrder.setOrderDate(request.getOrderDate());
        purchaseOrder.setNotes(request.getNotes());

        PurchaseOrder savedPurchaseOrder = purchaseOrderRepository.save(purchaseOrder);

        PurchaseOrder orderWithSupplier = purchaseOrderRepository.findById(savedPurchaseOrder.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Purchase order not found"));

        return new PurchaseOrderResponse(orderWithSupplier);
    }
}
