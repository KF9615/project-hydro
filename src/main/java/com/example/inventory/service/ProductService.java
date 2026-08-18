package com.example.inventory.service;

import com.example.inventory.dto.ProductResponse;
import com.example.inventory.dto.ProductRequest;
import com.example.inventory.entity.Category;
import com.example.inventory.entity.Product;
import com.example.inventory.repository.CategoryRepository;
import com.example.inventory.repository.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service

public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductService(
            ProductRepository productRepository,
            CategoryRepository categoryRepository) {

        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(ProductResponse::new)
                .toList();
    }

    // ProductRequest DTO -> Product entity -> MySQL

    public ProductResponse createProduct(ProductRequest request) {
        if (productRepository.existsBySkuIgnoreCase(request.getSku())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "A product with the same sku already exists");
        }

        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Category not found"));

        Product product = new Product();
        product.setSku(request.getSku());
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setUnitPrice(request.getUnitPrice());
        product.setReorderLevel(request.getReorderLevel());
        product.setCategory(category);

        if (request.getActive() != null) {
            product.setActive(request.getActive());
        }

        return new ProductResponse(productRepository.save(product));
    }

    public ProductResponse getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Product not found"));

        return new ProductResponse(product);
    }

    public ProductResponse updateProduct(Long id, ProductRequest request) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Product not found"));

        boolean skuHasChanged =
                !product.getSku().equalsIgnoreCase(request.getSku());

        if (skuHasChanged && productRepository.existsBySkuIgnoreCase(request.getSku())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "The sku already exists");
        }

        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Category not found"));

        product.setSku(request.getSku());
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setUnitPrice(request.getUnitPrice());
        product.setReorderLevel(request.getReorderLevel());
        product.setCategory(category);

        if (request.getActive() != null) {
            product.setActive(request.getActive());
        }

        return new ProductResponse(productRepository.save(product));
    }

    public void deleteProduct(Long id) {

        if (!productRepository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Product not found");
        }

        productRepository.deleteById(id);
    }
}
