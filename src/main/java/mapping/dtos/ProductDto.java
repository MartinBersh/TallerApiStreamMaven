package mapping.dtos;

public record ProductDto(Long id,
                         String name,
                         String category,
                         double price) {
}
