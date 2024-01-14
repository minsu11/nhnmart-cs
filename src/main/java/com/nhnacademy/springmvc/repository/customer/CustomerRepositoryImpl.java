package com.nhnacademy.springmvc.repository.customer;

import com.nhnacademy.springmvc.domain.Customer;
import com.nhnacademy.springmvc.domain.Role;
import com.nhnacademy.springmvc.exception.CustomerAlreadyExistsException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class CustomerRepositoryImpl implements CustomerRepository {

    private Map<String, Customer> customerMap = new HashMap<>();

    @Override
    public boolean exists(String id) {
        return customerMap.containsKey(id);
    }

    @Override
    public boolean matches(String id, String password) {
        return Optional.ofNullable(getCustomer(id))
                .map(customer -> customer.getPassword().equals(password))
                .orElse(false);
    }

    @Override
    public Customer register(String id, String password, String name, Role role) {
        if (exists(id)) {
            throw new CustomerAlreadyExistsException();
        }
        Customer customer = Customer.create(id, password);
        customer.setName(name);
        customer.setRole(role);

        customerMap.put(id, customer);
        return customer;
    }

    @Override
    public Customer getCustomer(String id) {
        return exists(id) ? customerMap.get(id) : null;
    }
}
