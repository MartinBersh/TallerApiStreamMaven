package servicesImpl.Impl.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import mapping.dtos.OrderDto;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;

public class Utils {
    private static final String userListSimulated = "userSimulatedList.json";

    public static List<OrderDto> getUserListSimulated() {
        ClassLoader classLoader = Utils.class.getClassLoader();
        URL resource = classLoader.getResource(userListSimulated);

        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            return mapper.readValue(new File(resource.getFile()), new TypeReference<>() {
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
