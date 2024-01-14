package com.nhnacademy.springmvc.controller;

import com.nhnacademy.springmvc.domain.Customer;
import com.nhnacademy.springmvc.domain.Role;
import com.nhnacademy.springmvc.repository.customer.CustomerRepository;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cs")
public class MainFormController {
    private CustomerRepository customerRepository;

    public MainFormController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @GetMapping("/")
    public String viewCustomer(HttpServletRequest request,
                               HttpServletResponse response) {
        HttpSession session = request.getSession();
        if (Objects.isNull(session.getAttribute("customerId"))) {
            throw new NullPointerException();
        }
        Customer customer = customerRepository.getCustomer((String) session.getAttribute("customerId"));

        return customer.getRole() == Role.CUSTOMER ? "thymeleaf/customerForm" : "thymeleaf/adminForm";
    }

}
