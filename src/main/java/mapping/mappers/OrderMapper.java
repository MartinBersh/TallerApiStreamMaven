package mapping.mappers;

import domain.models.Order;
import mapping.dtos.OrderDto;

import java.util.List;

public class OrderMapper {
    public static OrderDto mapFrom(Order source) {
        return new OrderDto(source.getId(),
                source.getStatus(),
                source.getOrderDate(),
                source.getDeliveryDate(),
                source.getProducts(),
                source.getCustomer());
    }

    public static Order mapFrom(OrderDto source) {
        return new Order(source.id(),
                source.status(),
                source.orderDate(),
                source.delivery(),
                source.products(),
                source.customer());
    }

    public static List<OrderDto> mapFrom(List<Order> source) {
        return source.parallelStream().map(e-> mapFrom(e)).toList();
    }
}

