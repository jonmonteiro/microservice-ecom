package venda_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import venda_service.entities.SaleProduct;

public interface ProductRepository extends JpaRepository<SaleProduct, Long> {

}
