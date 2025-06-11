package estoque_service.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import estoque_service.dto.ProductDTO;
import estoque_service.entities.StockProduct;
import estoque_service.repository.ProductRepository;

@Service
public class EstoqueListener {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ObjectMapper objectMapper;    @KafkaListener(topics = "product-topic", groupId = "estoque-group")
    public void handleProductCreation(String productJson) {
        System.out.println("Received from Kafka: " + productJson);
        try {
            ProductDTO productDTO = objectMapper.readValue(productJson, ProductDTO.class);
            
            StockProduct product = new StockProduct();
            product.setId(productDTO.getId());
            product.setName(productDTO.getName());
            product.setDescription(productDTO.getDescription());
            product.setPrice(productDTO.getPrice());
            product.setQuantity(productDTO.getQuantity());
            
            productRepository.save(product);
            System.out.println("Product saved in estoque-service with ID: " + product.getId());
        } catch (JsonProcessingException e) {
            System.err.println("Error processing product creation message: " + e.getMessage());
        }
    }
}
