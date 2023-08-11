package servicesImpl.Impl;

import mapping.dtos.OrderDto;
import repository.OrderRepository;
import servicesImpl.OrderService;

import java.util.List;

public class OrderServiceImpl implements OrderService {

    private OrderRepository repository;

    public OrderServiceImpl(OrderRepository repository) {
        this.repository = repository;
    }


    @Override
    public List<OrderDto> getAllOrders() {
        return repository.getAllOrders();
    }
}
