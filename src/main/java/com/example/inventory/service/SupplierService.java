package com.example.inventory.service;


import com.example.inventory.entity.Supplier;
import com.example.inventory.repository.SupplierRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class SupplierService {

    private final SupplierRepository supplierRepository;

    public SupplierService(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    public List<Supplier> getAllSuppliers() {
        return supplierRepository.findAll();
    }

    public Supplier createSupplier(Supplier supplier) {
        if(supplierRepository.existsBySupplierCodeIgnoreCase(supplier.getSupplierCode())) {
            throw new IllegalArgumentException("Supplier with this code already exists");
        }

        return supplierRepository.save(supplier);
    }

    public Supplier getSupplierById(Long id) {
        return supplierRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Supplier not found"));
    }

    public Supplier updateSupplier(Long id, Supplier updatedSupplier) {
        Supplier existingSupplier = getSupplierById(id);

        boolean codeHasChanged = !existingSupplier.getSupplierCode()
                .equalsIgnoreCase(updatedSupplier.getSupplierCode());

        if(codeHasChanged && supplierRepository.existsBySupplierCodeIgnoreCase(updatedSupplier.getSupplierCode())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "Supplier with this code already exists"
            );
        }

        existingSupplier.setSupplierCode(updatedSupplier.getSupplierCode());
        existingSupplier.setName(updatedSupplier.getName());
        existingSupplier.setContactPerson(updatedSupplier.getContactPerson());
        existingSupplier.setEmail(updatedSupplier.getEmail());
        existingSupplier.setContactNumber(updatedSupplier.getContactNumber());
        existingSupplier.setAddress(updatedSupplier.getAddress());
        existingSupplier.setActive(updatedSupplier.isActive());

        return supplierRepository.save(existingSupplier);
    }

    public void deleteSupplier(Long id) {
        if(!supplierRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Supplier not found");
        }
        supplierRepository.deleteById(id);
    }
}
