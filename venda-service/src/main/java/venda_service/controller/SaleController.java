package venda_service.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
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

    @DeleteMapping("/products/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) throws JsonProcessingException {
        String deleteMessage = objectMapper.writeValueAsString(new ProductDTO(id, null, null, null));
        kafkaTemplate.send("product-topic", deleteMessage);
        
        productRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<SaleProduct> updateProduct(@PathVariable Long id, @RequestBody ProductDTO productDTO) throws JsonProcessingException {
        return productRepository.findById(id)
            .map(product -> {
                product.setName(productDTO.name());
                product.setPrice(productDTO.price());
                product.setQuantity(productDTO.quantity());
                SaleProduct updatedProduct = productRepository.save(product);
                try {
                    String productJson = objectMapper.writeValueAsString(updatedProduct);
                    kafkaTemplate.send("product-topic", productJson);
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
                return ResponseEntity.ok(updatedProduct);
            })
            .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/products")
    public ResponseEntity<List<SaleProduct>> getProducts() {
        return ResponseEntity.ok(productRepository.findAll());
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<SaleProduct> getProduct(@PathVariable Long id) {
        return ResponseEntity.ok(productRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Product not found with ID: " + id)));
            
    }
}