package com.akart.product.service;

import com.akart.product.exception.ProductServiceResponseException;
import com.akart.product.model.Product;
import com.akart.product.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static org.apache.logging.log4j.util.Strings.isBlank;

@Service
@Slf4j
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    private void validateProduct(Product product) {
        log.info("Validating Product");
        if (isBlank(product.getName())) {
            throw ProductServiceResponseException.badRequest().message("name is required");
        }
        if (product.getPrice() < 1) {
            throw ProductServiceResponseException.badRequest().message("Product price cannot be 0");
        }

    }

    public List<Product> getAllProducts() {
        try {
            return productRepository.findAll();
        } catch (Exception e) {
            throw ProductServiceResponseException.internalServerError().message(e.getMessage());
        }
    }

    public Optional<Product> getProductById(String id) {
        try {
            return productRepository.findById(id);
        } catch (Exception e) {
            throw ProductServiceResponseException.internalServerError().message(e.getMessage());
        }
    }

    public Product createProduct(Product product) {
        validateProduct(product);
        try {
            return productRepository.save(product);
        } catch (Exception e) {
            throw ProductServiceResponseException.internalServerError().message(e.getMessage());
        }
    }

    public Product updateProduct(String id, Product updatedProduct) {
        validateProduct(updatedProduct);
        try {
            return productRepository.findById(id).map(product -> {
                product.setName(updatedProduct.getName());
                product.setDescription(updatedProduct.getDescription());
                product.setPrice(updatedProduct.getPrice());
                return productRepository.save(product);
            }).orElse(null);
        } catch (Exception e) {
            throw ProductServiceResponseException.internalServerError().message(e.getMessage());
        }
    }

    public boolean deleteProductById(String id) {
        try {
            productRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            throw ProductServiceResponseException.internalServerError().message(e.getMessage());
        }
    }
    
    

}
