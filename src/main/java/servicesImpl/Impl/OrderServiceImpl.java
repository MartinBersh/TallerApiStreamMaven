package servicesImpl.Impl;

import domain.models.Customer;
import domain.models.Order;
import domain.models.Product;
import mapping.dtos.OrderDto;
import mapping.dtos.ProductDto;
import repository.OrderRepository;
import repository.ProductRespository;
import repository.impl.OrderRepositoryImpl;
import repository.impl.ProductRespositoryImpl;
import servicesImpl.OrderService;
import servicesImpl.ProductService;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class OrderServiceImpl implements OrderService {

    List<OrderDto> orders;
    List<ProductDto> products;


    private OrderRepositoryImpl repository;
    public OrderServiceImpl(ProductService productService){
        OrderRepository repoOrder = new OrderRepositoryImpl();
        ProductRespository repoProd = new ProductRespositoryImpl();
        products = repoProd.getAllProducts();
        orders = repoOrder.getAllOrders();
    }

    @Override
    public List<OrderDto> getBaby() {
        return orders.stream()
                .filter(e -> e.products().stream()
                        .anyMatch(prod -> prod.getCategory().equalsIgnoreCase("Bebé")))
                .toList();
    }

    @Override
    public List<OrderDto> list3Latest() {
        return orders.stream()
                .sorted(Comparator.comparing(OrderDto::orderDate).reversed())
                .limit(3)
                .toList();

    }

    @Override
    public double avgSpecificDate() {
        List<OrderDto> listSpecificDate = orders.stream()
                .filter(e -> Objects.equals(e.orderDate(), LocalDate.of(2022, 3, 12)))
                .toList();

        return listSpecificDate.stream()
                .mapToDouble(e -> e.products().stream().mapToDouble(Product::getPrice).
                        average().orElse(0.0))
                .average().orElse(0.0);
    }

    @Override
    public double sumSpecificDate() {
        List<OrderDto> orderSpecificDate = orders.stream()
                .filter(e -> e.orderDate().getMonthValue() == 3)
                .filter(e -> e.orderDate().getYear() == 2022)
                .toList();

        return orderSpecificDate.stream()
                .mapToDouble(e -> e.products().stream().mapToDouble(Product::getPrice)
                        .sum())
                .sum();
    }

    @Override
    public Map<Customer, List<OrderDto>> ordersByTier() {
        return orders.stream()
                .collect(Collectors.groupingBy(OrderDto::customer));
    }

    @Override
    public List<ProductDto> filterTierDate() {
        return orders.stream()
                .filter(e -> e.customer().getTier() == 2)
                .filter(e -> e.orderDate().isAfter(LocalDate.of(2021, 2, 1)))
                .filter(e -> e.orderDate().isBefore(LocalDate.of(2021, 4, 1)))
                .flatMap(e -> e.products().stream())
                .distinct()
                .map(product -> new ProductDto(product.getId(), product.getName(),product.getCategory(),product.getPrice()))
                .toList();
    }

    private void launchException() {
        throw  new RuntimeException("Couldn't launch.'");
    }

    @Override
    public Double getSumSpecificDateThread() {
        sleepThread(1000);
        launchException();
        return getSumSpecificDateThread();
    }


    @Override
    public void sleepThread(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Double getAvgSpecificDateThread() {
        sleepThread(3000);
        return getAvgSpecificDateThread();    }
}
