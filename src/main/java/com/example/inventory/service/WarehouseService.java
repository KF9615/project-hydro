package com.example.inventory.service;

import com.example.inventory.entity.Warehouse;
import com.example.inventory.repository.WarehouseRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service

public class WarehouseService {

    private final WarehouseRepository warehouseRepository;

    public WarehouseService(WarehouseRepository warehouseRepository) {
        this.warehouseRepository = warehouseRepository;
    }

    public List<Warehouse> getAllWarehouses()
    {
        return warehouseRepository.findAll();
    }

    public Warehouse createWarehouse(Warehouse warehouse) {
        if(warehouseRepository.existsByWarehouseCodeIgnoreCase(warehouse.getWarehouseCode())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "Warehouse code already exists"
            );
        }

        return warehouseRepository.save(warehouse);
    }

    public Warehouse getWarehouseById(Long id) {
        return warehouseRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Warehouse not found"
                ));
    }

    public Warehouse updateWarehouse(Long id, Warehouse updatedWarehouse) {
        Warehouse existingWarehouse = getWarehouseById(id);

        boolean codeHasChanged = !existingWarehouse.getWarehouseCode().equalsIgnoreCase(updatedWarehouse.getWarehouseCode());

        if (codeHasChanged && warehouseRepository.existsByWarehouseCodeIgnoreCase(updatedWarehouse.getWarehouseCode())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "Warehouse code already exists"
            );
        }

        existingWarehouse.setWarehouseCode(updatedWarehouse.getWarehouseCode());
        existingWarehouse.setName(updatedWarehouse.getName());
        existingWarehouse.setLocationType(updatedWarehouse.getLocationType());
        existingWarehouse.setAddress(updatedWarehouse.getAddress());
        existingWarehouse.setContactNumber(updatedWarehouse.getContactNumber());
        existingWarehouse.setActive(updatedWarehouse.isActive());

        return warehouseRepository.save(existingWarehouse);
    }

    public void deleteWarehouseById(Long id) {
        if (!warehouseRepository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Warehouse not found");
        }

        warehouseRepository.deleteById(id);
    }
}
