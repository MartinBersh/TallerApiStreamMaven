
import servicesImpl.Impl.OrderServiceImpl;
import servicesImpl.Impl.ProductServiceImpl;
import servicesImpl.OrderService;
import servicesImpl.ProductService;

import java.util.*;
import java.util.concurrent.CompletableFuture;

public class Main {

    private static ProductService productService;
    private static OrderService orderService;

    public static void main(String[] args) {

        productService = new ProductServiceImpl();
        orderService = new OrderServiceImpl(productService);

        Scanner n = new Scanner(System.in);
        int opc = 0;

        while (opc != 16) {
            System.out.println("Digita el numero para hacer una consulta:" +
                    "\n Consultas -> " +
                    " \n 1. Lista de productos de la categoría 'Libros' con un precio superior a 100." +
                    " \n 2.  Lista de pedidos que incluyan productos de la categoría 'Bebé'." +
                    " \n 3. Mostrar la lista de productos de la categoría 'Juguetes' con un descuento del 10%. " +
                    " \n 4.Mostrar la lista de productos pedidos por clientes del nivel 2 entre el 01 de febrero de" +
                    " 2021 y el 01 de abril de 2021." +
                    " \n 5.  Mostrar la lista de productos de la categoría 'Libros' con los precios más bajos." +
                    " \n 6. Mostrar la lista de los 3 pedidos más recientes, basados en la fecha de la orden" +
                    " \n 7. Calcular la suma total de los pedidos realizados en una fecha específica." +
                    " \n 8. Calcular el promedio de pago en los pedidos en una fecha determinada." +
                    " \n 9. Generar un mapa de datos con registros de pedidos agrupados por cliente." +
                    " \n 10. Identificar el producto más costoso en cada categoría." +
                    " \n 11. Realizar la implementación de un proceso asíncrono utilizando runAsync." +
                    " \n 12. Ejecutar la implementación de un proceso asíncrono utilizando supplyAsync. " +
                    " \n 13. Ejecutar la implementación de thenAccept después de un proceso asíncrono. " +
                    " \n 14.  Realizar la implementación de thenApply y exceptionally después de un proceso asíncrono." +
                    " \n 15. Realizar la implementación de thenRun después de un proceso asíncrono." +
                    " \n 16. Salir.");
            opc = n.nextInt();

            switch (opc) {
                case 1 -> {
                    System.out.println(productService.filteredBooks());
                }
                case 2 -> {
                    System.out.println(orderService.getBaby());
                }
                case 3 -> {
                    System.out.println(productService.toysDiscount());
                }
                case 4 -> {
                    System.out.println(orderService.filterTierDate());
                }
                case 5 -> {
                    System.out.println(productService.listCheapest());
                }
                case 6 -> {
                    System.out.println(orderService.list3Latest());
                }
                case 7 -> {
                    System.out.println(orderService.sumSpecificDate());
                }
                case 8 -> {
                    System.out.println(orderService.avgSpecificDate());
                }
                case 9 -> {
                    System.out.println(orderService.ordersByTier());
                }
                case 10 -> {
                    System.out.println(productService.mostExpensive());
                }
                case 11 -> {
                    CompletableFuture.runAsync(() -> System.out.println(productService.getListCheapestThread()));
                    productService.sleepThread(5000);
                    System.out.println("Main thread finished");
                }
                case 12 -> {
                    System.out.println(CompletableFuture.supplyAsync(() -> productService.getToysDiscountThread()).join());
                }
                case 13 -> {
                    CompletableFuture.supplyAsync(() -> productService.getToysDiscountThread())
                            .thenAccept(System.out::println);
                    productService.sleepThread(7000);
                }
                case 14 -> {
                    CompletableFuture.supplyAsync(() -> orderService.getSumSpecificDateThread())
                            .thenApply(result -> result + orderService.getAvgSpecificDateThread())
                            .exceptionally(error -> 0.0)
                            .thenAccept(System.out::println);
                    orderService.sleepThread(4000);
                }
                case 15 -> {
                    CompletableFuture.supplyAsync(() -> orderService.getAvgSpecificDateThread())
                            .thenAccept(System.out::println)
                            .thenRun(() -> System.out.println(productService.getListCheapestThread()));
                    orderService.sleepThread(6000);
                }
                case 16 -> {
                    System.out.println("Cerrado");
                }
                default -> {
                    System.out.println("Número inválido");
                }
            }

        }
    }
}