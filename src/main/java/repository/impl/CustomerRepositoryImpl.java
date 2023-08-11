package repository.impl;

import domain.models.Customer;
import mapping.dtos.CustomerDto;
import mapping.mappers.CustomerMapper;
import repository.CustomerRepository;

import java.util.ArrayList;
import java.util.List;

public class CustomerRepositoryImpl implements CustomerRepository {

    private List<Customer> customers;

    public CustomerRepositoryImpl(){
        customers = new ArrayList<>();
        Customer c1 = new Customer(1L, "Daniel", 1);
        Customer c2 = new Customer(2L, "RIcardo", 2);
        Customer c3 = new Customer(3L, "Juan", 3);

        customers.add(c1);
        customers.add(c2);
        customers.add(c3);
    }


    @Override
    public List<CustomerDto> getAllCustomers() {
        return CustomerMapper.mapFrom(customers);
    }
}
