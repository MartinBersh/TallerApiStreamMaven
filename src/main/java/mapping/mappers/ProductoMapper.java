package mapping.mappers;

import domain.models.Customer;
import domain.models.Product;
import mapping.dtos.CustomerDto;
import mapping.dtos.ProductDto;

import java.util.List;

public class ProductoMapper {

    public static ProductDto mapFrom(Product source){
        return new ProductDto(source.getId(),
                source.getName(),
                source.getCategory(),
                source.getPrice());
    }

    public static Product mapFrom(ProductDto source){
        return  new Product(source.id(),
                source.name(),
                source.category(),
                source.price());
    }

    public static List<ProductDto> mapFrom(List<Product> source) {
        return source.parallelStream().map(e-> mapFrom(e)).toList();
    }

    }
