package servicesImpl.Impl;

import mapping.dtos.OrderDto;
import mapping.dtos.ProductDto;
import repository.ProductRespository;
import repository.impl.ProductRespositoryImpl;
import servicesImpl.ProductService;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class ProductServiceImpl implements ProductService {

        List<ProductDto> products;
        List<OrderDto> orders;

        public ProductServiceImpl() {
            ProductRespository repo = new ProductRespositoryImpl();
            products = repo.getAllProducts();
        }


    @Override
    public List<ProductDto> filteredBooks() {
        return products.stream()
                .filter(e -> e.category().equalsIgnoreCase("Libros"))
                .filter(e -> e.price() > 100)
                .toList();
    }

    @Override
    public List<ProductDto> toysDiscount() {
        return products.stream()
                .filter(e -> e.category().equalsIgnoreCase("Juguetes"))
                .map(dis -> {
                    double discountedPrice = dis.price() * 0.10;
                    return new ProductDto(dis.id(),dis.name(), dis.category(), dis.price() - discountedPrice);
                })
                .toList();
    }

    @Override
    public ProductDto listCheapest() {
        return products.stream()
                .filter(e -> e.category().equalsIgnoreCase("Libros"))
                .min(Comparator.comparing(ProductDto::price)).orElse(null);
    }

    @Override
    public Map<String, Optional<ProductDto>> mostExpensive() {
        return products.stream()
                .collect(Collectors.groupingBy(ProductDto::category,Collectors.
                        maxBy(Comparator.comparing(ProductDto::price))));
    }

    @Override
    public String getListCheapestThread() {
        sleepThread(3000);
        return "Thread ended "+ listCheapest();
    }

    @Override
    public List<ProductDto> getToysDiscountThread() {
        sleepThread(4000);
        return toysDiscount();    }

    @Override
    public void sleepThread(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
