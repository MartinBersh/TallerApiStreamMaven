package repository.impl;

import domain.models.Product;
import mapping.dtos.ProductDto;
import mapping.mappers.ProductMapper;
import repository.ProductRespository;

import java.util.ArrayList;
import java.util.List;

public class ProductRespositoryImpl implements ProductRespository {

    private List<Product> products;

    public ProductRespositoryImpl() {
        products = new ArrayList<Product>();
        Product product1 = new Product(1L, "Don quijote", "Libros", 30000.0);
        Product product2 = new Product(2L, "La naranja mecanica", "Libros", 120000.0);
        Product product3 = new Product(3L, "El Hobbit", "Libros", 110000.0);
        Product product4 = new Product(4L, "El principito", "Libros", 30000.0);
        Product product5 = new Product(5L, "Tetero", "Bebé", 10000.0);
        Product product6 = new Product(6L, "Cuna", "Bebé", 25000.0);
        Product product7 = new Product(7L, "Barbie", "Juguetes", 35000.0);
        Product product8 = new Product(8L, "Monopoly", "Juguetes", 43000.0);
        Product product9 = new Product(9L, "La biblia", "Libros", 315000.0);
        Product product10 = new Product(10L, "Harry potter", "Libros", 45000.0);
        products = List.of(product1,product2,product3,product4,product5,product6,product7,product8,product9,product10);

    }


    @Override
    public List<ProductDto> getAllProducts() {
        return ProductMapper.mapFrom(products);
    }
}
