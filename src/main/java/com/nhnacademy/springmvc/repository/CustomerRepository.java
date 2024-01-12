package com.nhnacademy.springmvc.repository;

import com.nhnacademy.springmvc.domain.Customer;
import com.nhnacademy.springmvc.domain.Role;

public interface CustomerRepository {
    boolean exists(String id);

    Customer register(String id, String password, String name, Role role);

    Customer getCustomer(String id);

}
