package com.nhnacademy.springmvc.controller;

import com.nhnacademy.springmvc.repository.CustomerRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cs")
public class LoginController {
    private CustomerRepository customerRepository;

    public LoginController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @GetMapping("/login")
    public String login() {
        return "thymeleaf/loginForm";
    }

    @PostMapping("/login")
    public String doLogin(){
        return
    }

}
