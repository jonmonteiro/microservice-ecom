package estoque_service.controller;

import estoque_service.entities.StockProduct;
import estoque_service.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    public ResponseEntity<List<StockProduct>> listAllProducts() {
        List<StockProduct> products = productRepository.findAll();
        return ResponseEntity.ok(products);
    }
}
