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
public class StockListener {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @KafkaListener(topics = "product-topic", groupId = "estoque-group")
    public void handleProductCreation(String productJson) {
        try {
            //Converts the received JSON string into a ProductDTO object
            ProductDTO productDTO = objectMapper.readValue(productJson, ProductDTO.class);
            
            StockProduct product = productRepository.findById(productDTO.id())
                .orElse(new StockProduct());
            
            product.setName(productDTO.name());
            product.setPrice(productDTO.price());
            product.setQuantity(productDTO.quantity());

            productRepository.save(product);
            System.out.println("Product saved in estoque-service with ID: " + product.getId());
        } catch (JsonProcessingException e) {
            System.err.println("Error processing product creation message: " + e.getMessage());
        }
    }
}
