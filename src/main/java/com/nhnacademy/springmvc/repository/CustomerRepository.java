package com.nhnacademy.springmvc.repository;

import com.nhnacademy.springmvc.domain.Customer;

public interface CustomerRepository {
    boolean exists(String id);

    Customer register(String id, String password);

    Customer getCustomer(String id);

}
