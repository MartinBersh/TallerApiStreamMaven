package servicesImpl.Impl;

import mapping.dtos.OrderDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import servicesImpl.Impl.OrderServiceImpl;
import servicesImpl.Impl.ProductServiceImpl;
import servicesImpl.Impl.utils.Utils;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


class OrderServiceImplTest {

    OrderServiceImpl orderService;
    ProductServiceImpl productService;
    @BeforeEach
    void setUp() {
        productService = new ProductServiceImpl();
        orderService = new OrderServiceImpl(productService);
    }

    @Test
    void getBaby() {
        List<OrderDto> expectedOrders = Utils.getUserListSimulated();
        List<OrderDto> result = orderService.getBaby();
        assertEquals(expectedOrders.size(), result.size(),"La funcion no se esta desarrollando correctamente.");
    }


}