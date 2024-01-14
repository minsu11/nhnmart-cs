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

    public static Customer create(String id, String password) {
        return new Customer(id, password);
    }

    private Customer(String id, String password) {
        this.id = id;
        this.password = password;
    }


}
