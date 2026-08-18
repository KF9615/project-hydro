package com.example.inventory.repository;

import com.example.inventory.entity.Batch;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BatchRepository extends JpaRepository<Batch, Long> {

    boolean existsByBatchNumberIgnoreCase(String batchNumber);

    @Override
    @EntityGraph(attributePaths = "product")
    List<Batch> findAll();

    @Override
    @EntityGraph(attributePaths = "product")
    Optional<Batch> findById(Long id);
}
