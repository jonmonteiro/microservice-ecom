package venda_service.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import venda_service.dto.ProductDTO;
import venda_service.entities.SaleProduct;
import venda_service.service.SaleService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SaleController {
    @Autowired
    private SaleService saleService;

    @PostMapping("/products")
    public ResponseEntity<SaleProduct> createProduct(@RequestBody ProductDTO productDTO) throws JsonProcessingException {
        SaleProduct savedProduct = saleService.createProduct(productDTO);
        return ResponseEntity.ok(savedProduct);
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) throws JsonProcessingException {
        saleService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<SaleProduct> updateProduct(@PathVariable Long id, @RequestBody ProductDTO productDTO) throws JsonProcessingException {
        SaleProduct updatedProduct = saleService.updateProduct(id, productDTO);
        return ResponseEntity.ok(updatedProduct);
    }

    @GetMapping("/products")
    public ResponseEntity<List<SaleProduct>> getAllProducts() {
        return ResponseEntity.ok(saleService.getAllProducts());
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<SaleProduct> getProduct(@PathVariable Long id) {
        return ResponseEntity.ok(saleService.getProduct(id));
    }
}