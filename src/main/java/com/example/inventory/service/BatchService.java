package com.example.inventory.service;

import com.example.inventory.dto.BatchResponse;
import com.example.inventory.dto.BatchRequest;
import com.example.inventory.entity.Batch;
import com.example.inventory.entity.Product;
import com.example.inventory.repository.BatchRepository;
import com.example.inventory.repository.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class BatchService {
    private BatchRepository batchRepository;
    private final ProductRepository productRepository;

    public BatchService(BatchRepository batchRepository, ProductRepository productRepository) {
        this.batchRepository = batchRepository;
        this.productRepository = productRepository;
    }

    public List<BatchResponse> getAllBatches() {
        return batchRepository.findAll()
                .stream()
                .map(BatchResponse::new)
                .toList();
    }

    public BatchResponse createBatch(BatchRequest request) {
        if(batchRepository.existsByBatchNumberIgnoreCase(request.getBatchNumber())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "Batch number already exists"
            );
        }

        if(request.getManufacturingDate() !=null && !request.getExpiryDate()
                .isAfter(request.getManufacturingDate())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Manufacturing Date must be after Expiry Date"
            );
        }

        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));

        Batch batch = new Batch();
        batch.setBatchNumber(request.getBatchNumber());
        batch.setProduct(product);
        batch.setManufacturingDate(request.getManufacturingDate());
        batch.setExpiryDate(request.getExpiryDate());

        if(request.getActive() != null) {
            batch.setActive(request.getActive());
        }

        return new BatchResponse(batchRepository.save(batch));
    }

    public BatchResponse getBatchById(Long id) {
        Batch batch = batchRepository.findById(id).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Batch not found"));

        return new BatchResponse(batch);
    }

    public BatchResponse updateBatch(Long id, BatchRequest request) {
        Batch existingBatch = batchRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Batch not found"));

        boolean batchNumberChanged =!existingBatch.getBatchNumber().equalsIgnoreCase(request.getBatchNumber());

        if (batchNumberChanged && batchRepository.existsByBatchNumberIgnoreCase(request.getBatchNumber())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "Batch number already exists"
            );
        }

        if (request.getManufacturingDate() != null
            && !request.getExpiryDate().isAfter(request.getManufacturingDate())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Manufacturing Date must be after Expiry Date"
            );
            }
        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));

        existingBatch.setBatchNumber(request.getBatchNumber());
        existingBatch.setProduct(product);
        existingBatch.setManufacturingDate(request.getManufacturingDate());
        existingBatch.setExpiryDate(request.getExpiryDate());

        if (request.getActive() != null) {
            existingBatch.setActive(request.getActive());
        }

        Batch savedBatch = batchRepository.save(existingBatch);

        Batch batchWithProduct = batchRepository.findById(savedBatch.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Batch not found"));

        return new BatchResponse(batchWithProduct);
    }

    public void deleteBatchById(Long id) {
        Batch batch = batchRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Batch not found"));

        batchRepository.delete(batch);
    }
}
