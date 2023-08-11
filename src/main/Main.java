import domain.models.Customer;
import domain.models.Order;
import domain.models.Product;
import mapping.dtos.AppDto;
import repository.AppRepository;
import repository.impl.AppRepositoryImpl;
import servicesImpl.AppService;
import servicesImpl.Impl.AppServiceImpl;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;


public class Main {
    public static void main(String[] args) {

        List<Product> products = getProductList();

        List<Customer> customers = getCustomerList();

        List<Order> orders = new ArrayList<>();
        orders.addAll(createOrder(products.subList(0, 3), customers.get(0)).subList(0, 1));
        orders.addAll(createOrder(products.subList(3, 4), customers.get(1)).subList(1, 2));
        orders.addAll(createOrder(products.subList(3, 5), customers.get(4)).subList(2, 3));
        orders.addAll(createOrder(products.subList(5, 7), customers.get(2)).subList(3, 4));
        orders.addAll(createOrder(products.subList(7, 9), customers.get(3)).subList(4, 5));
        orders.addAll(createOrder(products.subList(8, 10), customers.get(0)).subList(5, 6));
        orders.addAll(createOrder(products.subList(4, 6), customers.get(1)).subList(6, 7));


        //P1
        List<Product> filteredBooks = products.stream()
                .filter(e -> e.getCategory().equalsIgnoreCase("Libros"))
                .filter(e -> e.getPrice() > 100000)
                .toList();

        System.out.println("Productos filtrados por categoria Libros y precio mayor a 100: ");
        System.out.println(filteredBooks);
        System.out.println("----------------------------------------------------------------");

        //P2
        List<Order> babys = orders.stream()
                .filter(e -> e.getProducts().stream()
                        .anyMatch(prod -> prod.getCategory().equalsIgnoreCase("Bebé")))
                .toList();
        System.out.println("Productos filtrados por la categoria Bebé: ");
        System.out.println(babys);
        System.out.println("----------------------------------------------------------------");

        //P3
        List<Product> toys = products.stream()
                .filter(e -> e.getCategory().equalsIgnoreCase("Juguetes"))
                .peek(dis -> {
                    double discountedPrice = dis.getPrice() * 0.10;
                    dis.setPrice(dis.getPrice() - discountedPrice);
                })
                .toList();
        System.out.println("Productos filtrados de la categoria 'Juguetes' con un 10% de descuento: ");
        System.out.println(toys);
        System.out.println("----------------------------------------------------------------");


        //P5
        List<Product> listCheapest = products.stream()
                .filter(e -> e.getCategory().equalsIgnoreCase("Libros"))
                .min(Comparator.comparing(Product::getPrice)).stream().toList();

        System.out.println("Lista filtrada de los Libros mas baratos: ");
        System.out.println(listCheapest);
        System.out.println("----------------------------------------------------------------");

        //P6

        List<Order> list3Latest = orders.stream()
                .sorted(Comparator.comparing(Order::getOrderDate).reversed())
                .limit(3)
                .toList();

        System.out.println("Los tres pedidos más recientes son: ");
        System.out.println(list3Latest);
        System.out.println("----------------------------------------------------------------");


        //P8
        List<Order> listSpecificDate = orders.stream()
                .filter(e -> Objects.equals(e.getOrderDate(), LocalDate.of(2022, 3, 12)))
                .toList();

        double avgSpecificDate = listSpecificDate.stream()
                .mapToDouble(e -> e.getProducts().stream().mapToDouble(Product::getPrice).
                        average().orElse(0.0))
                .average().orElse(0.0);
        System.out.println("Promedio de pago en los pedidos en la fecha 12/03/2022 :");
        System.out.println(avgSpecificDate);
        System.out.println("----------------------------------------------------------------");

        //P4
        List<Product> filterTierDate = orders.stream()
                .filter(e -> e.getCustomer().getTier() == 2)
                .filter(e -> e.getOrderDate().isAfter(LocalDate.of(2021, 2, 1)))
                .filter(e -> e.getOrderDate().isBefore(LocalDate.of(2021, 4, 1)))
                .flatMap(e -> e.getProducts().stream())
                .distinct()
                .toList();

        System.out.println("Productos pedidos por clientes de Tier 2 entre 2 fechas: ");
        System.out.println(filterTierDate);
        System.out.println("----------------------------------------------------------------");



        //P10
        Map<String, Optional<Product>> mostExpensive = products.stream()
                .collect(Collectors.groupingBy(Product::getCategory,Collectors.
                        maxBy(Comparator.comparing(Product::getPrice))));
        System.out.println(mostExpensive);

        //P9
        Map<Customer, List<Order>> ordersByTier = orders.stream()
                .collect(Collectors.groupingBy(Order::getCustomer));

        System.out.println("Mapa de datos con los pedidos agrupados por cliente: ");
        System.out.println(ordersByTier);
        System.out.println("----------------------------------------------------------------");

        //P7

        List<Order> orderSpecificDate = orders.stream()
                .filter(e -> e.getOrderDate().getMonthValue() == 3)
                .filter(e -> e.getOrderDate().getYear() == 2022)
                .toList();

        double sumSpecificDate = orderSpecificDate.stream()
                .mapToDouble(e -> e.getProducts().stream().mapToDouble(Product::getPrice)
                        .sum())
                .sum();

        System.out.println("Pedidos hechos en el mes de Marzo en 2022:");
        System.out.println(sumSpecificDate);
        System.out.println("----------------------------------------------------------------");


    }


    private static List<Customer> getCustomerList(){

        Customer c1 = new Customer(1L, "Daniel", 1);
        Customer c2 = new Customer(2L, "RIcardo", 2);
        Customer c3 = new Customer(3L, "Juan", 3);
        Customer c4 = new Customer(4L, "Felpe",1);
        Customer c5 = new Customer(5L, "Martin",2);

        return List.of(c1, c2, c3, c4, c5);
    }


    private static List<Order> createOrder(List<Product> products, Customer customer) {
        Order order1 = new Order(1L,"Processed",LocalDate.of(2021,2,15)
                ,LocalDate.now(),products,customer);
        Order order2 = new Order(2L,"Processed",LocalDate.of(2021,3,16)
                ,LocalDate.now(),products,customer);
        Order order3 = new Order(3L,"Processed",LocalDate.now(),LocalDate.now(),products,customer);
        Order order4 = new Order(4L,"Processed",LocalDate.of(2023,5,16)
                ,LocalDate.now(),products,customer);
        Order order5 = new Order(5L,"Processed",LocalDate.of(2023,6,26)
                ,LocalDate.now(),products,customer);
        Order order6 = new Order(6L,"Processed",LocalDate.of(2022,3,12)
                ,LocalDate.now(),products,customer);
        Order order7 = new Order(7L,"Processed",LocalDate.of(2022,3,12)
                ,LocalDate.now(),products,customer);

        return List.of(order1, order2, order3, order4, order5, order6,order7);
    }

}