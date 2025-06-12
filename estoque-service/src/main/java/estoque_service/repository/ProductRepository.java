package estoque_service.repository;

import estoque_service.entities.StockProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<StockProduct, Long> {

}
