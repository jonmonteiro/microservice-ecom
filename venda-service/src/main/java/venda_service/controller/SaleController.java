package venda_service.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;
import venda_service.dto.ProductDTO;
import venda_service.entities.SaleProduct;
import venda_service.repository.ProductRepository;

@RestController
@RequestMapping("/api")
public class SaleController {
    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    ObjectMapper objectMapper;

    @PostMapping("/products")
    public ResponseEntity<SaleProduct> createProduct(@RequestBody ProductDTO productDTO) throws JsonProcessingException {
        SaleProduct saleProduct = new SaleProduct();

        saleProduct.setId(productDTO.id());
        saleProduct.setName(productDTO.name());
        saleProduct.setPrice(productDTO.price());
        saleProduct.setQuantity(productDTO.quantity());

        SaleProduct savedProduct = productRepository.save(saleProduct);

        String productJson = objectMapper.writeValueAsString(savedProduct);
        kafkaTemplate.send("product-topic", productJson);
        
        return ResponseEntity.ok(savedProduct);
    }
}