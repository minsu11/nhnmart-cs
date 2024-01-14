package com.nhnacademy.springmvc.repository.customer;

import com.nhnacademy.springmvc.domain.Customer;
import com.nhnacademy.springmvc.domain.Role;

public interface CustomerRepository {
    boolean exists(String id);

    boolean matches(String id, String password);

    Customer register(String id, String password, String name, Role role);

    Customer getCustomer(String id);


}
