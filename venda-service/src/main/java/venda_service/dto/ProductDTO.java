package venda_service.dto;

public record ProductDTO(
    Long id,
    String name,
    Double price,
    Integer quantity
) {
   
}
