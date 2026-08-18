package com.example.inventory.service;

import com.example.inventory.dto.PurchaseOrderItemRequest;
import com.example.inventory.dto.PurchaseOrderItemResponse;
import com.example.inventory.entity.PurchaseOrderItem;
import com.example.inventory.entity.Product;
import com.example.inventory.entity.PurchaseOrder;
import com.example.inventory.repository.ProductRepository;
import com.example.inventory.repository.PurchaseOrderItemRepository;
import com.example.inventory.repository.PurchaseOrderRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class PurchaseOrderItemService {

    private final PurchaseOrderItemRepository purchaseOrderItemRepository;
    private final PurchaseOrderRepository purchaseOrderRepository;
    private final ProductRepository productRepository;

    public PurchaseOrderItemService(
            PurchaseOrderItemRepository purchaseOrderItemRepository,
            PurchaseOrderRepository purchaseOrderRepository,
            ProductRepository productRepository) {

        this.purchaseOrderItemRepository = purchaseOrderItemRepository;
        this.purchaseOrderRepository = purchaseOrderRepository;
        this.productRepository = productRepository;
    }

    public List<PurchaseOrderItemResponse> getAllPurchaseOrderItems() {

        return purchaseOrderItemRepository.findAll()
                .stream()
                .map(PurchaseOrderItemResponse::new)
                .toList();
    }

    public List<PurchaseOrderItemResponse> getItemsByPurchaseOrder(Long purchaseOrderId) {

        if (!purchaseOrderRepository.existsById(purchaseOrderId)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Purchase order not found"
            );
        }

        return purchaseOrderItemRepository
                .findByPurchaseOrderId(purchaseOrderId)
                .stream()
                .map(PurchaseOrderItemResponse::new)
                .toList();
    }

    public PurchaseOrderItemResponse createPurchaseOrderItem(PurchaseOrderItemRequest request) {

        PurchaseOrder purchaseOrder = purchaseOrderRepository.findById(request.getPurchaseOrderId())
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND, "Purchase order not found"
                        ));

        Product product = productRepository
                .findById(request.getProductId())
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND, "Product not found"
                        ));

        PurchaseOrderItem purchaseOrderItem = new PurchaseOrderItem();

        purchaseOrderItem.setPurchaseOrder(purchaseOrder);
        purchaseOrderItem.setProduct(product);
        purchaseOrderItem.setQuantityOrdered(request.getQuantityOrdered());
        purchaseOrderItem.setUnitCost(request.getUnitCost());

        PurchaseOrderItem savedItem = purchaseOrderItemRepository.save(purchaseOrderItem);

        return new PurchaseOrderItemResponse(savedItem);
    }
}
