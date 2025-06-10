package venda_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VendaController {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @PostMapping("/sale")
    public ResponseEntity<Void> registerSale(@RequestBody String sale) {
        kafkaTemplate.send("estoque-topico", sale);
        return ResponseEntity.ok().build();
    }
}