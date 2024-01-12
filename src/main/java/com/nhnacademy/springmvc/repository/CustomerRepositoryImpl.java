package com.nhnacademy.springmvc.repository;

import com.nhnacademy.springmvc.domain.Customer;

public class CustomerRepositoryImpl implements CustomerRepository {
    @Override
    public boolean exists(String id) {
        return false;
    }

    @Override
    public Customer register(String id, String password) {
        return null;
    }

    @Override
    public Customer getCustomer(String id) {
        return null;
    }
}
