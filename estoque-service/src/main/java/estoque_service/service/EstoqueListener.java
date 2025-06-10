package estoque_service.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class EstoqueListener {

    @KafkaListener(topics = "estoque-topico", groupId = "estoque-group")
    public void ProcessSale(String message) {
        System.out.println("Received message: " + message);
    }

}
