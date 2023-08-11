package servicesImpl;

import mapping.dtos.OrderDto;

import java.util.List;
public interface OrderService {
    List<OrderDto> getAllOrders();
}
