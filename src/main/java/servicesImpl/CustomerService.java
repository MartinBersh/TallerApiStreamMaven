package servicesImpl;

import mapping.dtos.ProductDto;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface CustomerService {
    List<ProductDto> getProdFromCategorPrice();
    List<ProductDto> getProdFromCategoryApplyDiscount();
    ProductDto getCheapestProduct();
    Map<String, Optional<ProductDto>> getMostExpensiveProduct();

    String getCheapestProductThread();
    List<ProductDto> getProdFromCategoryApplyDiscountThread();
    void sleepThread(int millis);
}
