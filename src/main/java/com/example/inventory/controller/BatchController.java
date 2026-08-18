package com.example.inventory.controller;

import com.example.inventory.dto.BatchRequest;
import com.example.inventory.dto.BatchResponse;
import com.example.inventory.service.BatchService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/batches")
public class BatchController {

    private final BatchService batchService;

    public BatchController(BatchService batchService) {
        this.batchService = batchService;
    }

    @GetMapping
    public List<BatchResponse> getAllBatches() {
        return batchService.getAllBatches();
    }

    @GetMapping("/{id}")
    public BatchResponse getBatchById(@PathVariable Long id) {
        return batchService.getBatchById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BatchResponse createBatch(@Valid @RequestBody BatchRequest request) {
        return batchService.createBatch(request);
    }

    @PutMapping("/{id}")
    public BatchResponse updateBatch(@PathVariable Long id, @Valid @RequestBody BatchRequest request) {

        return batchService.updateBatch(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBatch(@PathVariable Long id) {
        batchService.deleteBatchById(id);
    }
}
