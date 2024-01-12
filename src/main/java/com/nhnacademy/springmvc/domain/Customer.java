package com.nhnacademy.springmvc.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Customer {

    private String id;
    private String password;
    private String name;

    private Role role;
    private static final String MASK = "*****";

    public static Customer create(String id, String password) {
        return new Customer(id, password);
    }

    private Customer(String id, String password) {
        this.id = id;
        this.password = password;
    }

    public static Customer constructPasswordMskedCustomer(Customer customer) {
        Customer newCustomer = Customer.create(customer.getId(), MASK);
        newCustomer.setName(customer.getName());
        newCustomer.setRole(customer.getRole());
        return newCustomer;
    }


}
