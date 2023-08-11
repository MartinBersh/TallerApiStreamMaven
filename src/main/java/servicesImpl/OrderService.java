package servicesImpl;

import domain.models.Customer;
import mapping.dtos.OrderDto;
import mapping.dtos.ProductDto;

import java.util.List;
import java.util.Map;

public interface OrderService {
    List<OrderDto> getBaby();
    List<OrderDto> list3Latest();
    double avgSpecificDate();
    double sumSpecificDate();
    Map<Customer,List<OrderDto>> ordersByTier();
    List<ProductDto> filterTierDate();

    Double getSumSpecificDateThread();
    void sleepThread(int millis);
    Double getAvgSpecificDateThread();
}
