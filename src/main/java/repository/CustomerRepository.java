package repository;

import mapping.dtos.CustomerDto;

import java.util.List;

public interface CustomerRepository {

    public List<CustomerDto> getAllCustomers();

}
