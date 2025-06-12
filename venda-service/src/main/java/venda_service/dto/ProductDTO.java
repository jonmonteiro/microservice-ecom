package venda_service.dto;

import venda_service.entities.SaleProduct;

public record ProductDTO(
    Long id,
    String name,
    Double price,
    Integer quantity
) {
    public ProductDTO(SaleProduct product) {
        this(product.getId(), product.getName(), product.getPrice(), product.getQuantity());
    }
}
