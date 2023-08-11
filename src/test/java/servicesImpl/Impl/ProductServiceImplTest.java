package servicesImpl.Impl;

import mapping.dtos.OrderDto;
import mapping.dtos.ProductDto;
import org.junit.jupiter.api.Test;
import servicesImpl.Impl.utils.Utils;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductServiceImplTest {

    @Test
    void filteredBooks(){
        ProductServiceImpl productService = new ProductServiceImpl();
        List<OrderDto> expected = Utils.getUserListSimulated();
        List<ProductDto> result = productService.filteredBooks();
        assertEquals(expected.size(), result.size(),"Esta funcion no esta desarrollandose de forma correcta");
    }

}