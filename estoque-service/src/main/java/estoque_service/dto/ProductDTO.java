package estoque_service.dto;

import estoque_service.entities.StockProduct;

public record ProductDTO(
    Long id,
    String name,
    Double price,
    Integer quantity
) {
    public ProductDTO(StockProduct product) {
        this(product.getId(), product.getName(), product.getPrice(), product.getQuantity());
    }
}
