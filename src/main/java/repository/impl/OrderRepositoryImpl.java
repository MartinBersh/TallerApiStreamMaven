package repository.impl;

import domain.models.Customer;
import domain.models.Order;
import domain.models.Product;
import mapping.dtos.CustomerDto;
import mapping.dtos.OrderDto;
import mapping.dtos.ProductDto;
import mapping.mappers.CustomerMapper;
import mapping.mappers.OrderMapper;
import mapping.mappers.ProductoMapper;
import repository.CustomerRepository;
import repository.OrderRepository;
import repository.ProductRespository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrderRepositoryImpl implements OrderRepository {

    List<Product> products;
    List<Customer> customer;
    List<Order> orders;

    public OrderRepositoryImpl(){
        CustomerRepositoryImpl customerRepo = new CustomerRepositoryImpl();
        ProductRespositoryImpl productRepo = new ProductRespositoryImpl();
        customer = CustomerMapper.mapFromDto(customerRepo.getAllCustomers());
        products = ProductoMapper.mapFromDto(productRepo.getAllProducts());

        Order order1 = new Order(1L,"Processed",LocalDate.of(2021,2,15)
                ,LocalDate.now(),products.subList(2,10),customer.get(3));
        Order order2 = new Order(2L,"Processed",LocalDate.of(2021,3,16)
                ,LocalDate.now(),products.subList(3,5),customer.get(1));
        Order order3 = new Order(3L,"Processed",LocalDate.now(),LocalDate.now(),products.subList(1,2),customer.get(3));
        Order order4 = new Order(4L,"Processed",LocalDate.of(2023,5,16)
                ,LocalDate.now(),products.subList(5,9),customer.get(2));
        Order order5 = new Order(5L,"Processed",LocalDate.of(2023,6,26)
                ,LocalDate.now(),products.subList(2,4),customer.get(1));
        Order order6 = new Order(6L,"Processed",LocalDate.of(2022,3,12)
                ,LocalDate.now(),products.subList(7,5),customer.get(2));
        Order order7 = new Order(7L,"Processed",LocalDate.of(2022,3,12)
                , LocalDate.now(),products.subList(3,8),customer.get(3));

        orders = List.of(order1, order2, order3, order4, order5, order6,order7);
    }

    @Override
    public List<OrderDto> getAllOrders() {
        return OrderMapper.mapFrom(orders);
    }

}
