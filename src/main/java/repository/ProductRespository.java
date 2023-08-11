package repository;

import domain.models.Product;
import mapping.dtos.ProductDto;

import java.util.List;

public interface ProductRespository {

    public List<ProductDto> getAllProducts();

}
