package com.nhnacademy.springmvc.repository;

import com.nhnacademy.springmvc.domain.Customer;
import com.nhnacademy.springmvc.domain.Role;
import com.nhnacademy.springmvc.exception.CustomerAlreadyExistsException;
import com.nhnacademy.springmvc.exception.CustomerNotFoundException;
import java.util.HashMap;
import java.util.Map;

public class CustomerRepositoryImpl implements CustomerRepository {

    private Map<String, Customer> customerMap = new HashMap<>();

    @Override
    public boolean exists(String id) {
        return customerMap.containsKey(id);
    }

    @Override
    public Customer register(String id, String password, String name, Role role) {
        if (exists(id)) {
            throw new CustomerAlreadyExistsException();
        }
        Customer customer = Customer.create(id, password);
        customer.setName(name);
        customer.setRole(role);
        return customer;
    }

    @Override
    public Customer getCustomer(String id) {
        if (exists(id)) {
            throw new CustomerNotFoundException();
        }
        return customerMap.get(id);
    }
}
