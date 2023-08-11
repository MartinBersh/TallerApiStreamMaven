package servicesImpl;

import mapping.dtos.ProductDto;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ProductService {

    List<ProductDto> filteredBooks();
    List<ProductDto> toysDiscount();
    ProductDto listCheapest();
    Map<String, Optional<ProductDto>> mostExpensive();

    String getListCheapestThread();
    List<ProductDto> getToysDiscountThread();
    void sleepThread(int millis);

}
