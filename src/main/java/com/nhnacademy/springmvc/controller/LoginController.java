package com.nhnacademy.springmvc.controller;

import com.nhnacademy.springmvc.domain.Customer;
import com.nhnacademy.springmvc.domain.Role;
import com.nhnacademy.springmvc.exception.LogoutException;
import com.nhnacademy.springmvc.repository.customer.CustomerRepository;
import com.nhnacademy.springmvc.repository.inquiry.InquiryRepository;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/cs")
public class LoginController {
    private CustomerRepository customerRepository;
    private InquiryRepository inquiryRepository;

    public LoginController(CustomerRepository customerRepository, InquiryRepository inquiryRepository) {
        this.customerRepository = customerRepository;
        this.inquiryRepository = inquiryRepository;
    }


    @GetMapping("/login")
    public String login() {
        return "thymeleaf/loginForm";
    }

    @PostMapping("/login")
    public String doLogin(@RequestParam("id") String id,
                          @RequestParam("password") String password,
                          HttpServletRequest request,
                          HttpServletResponse response,
                          ModelMap modelMap) {
        if (customerRepository.matches(id, password)) {
            HttpSession session = request.getSession();
            session.setAttribute("customerId", id);
            session.setMaxInactiveInterval(1800);

            Customer customer = customerRepository.getCustomer(id);
            String path = customer.getRole().equals(Role.ADMIN) ? "thymeleaf/adminForm" : "thymeleaf/customerForm";
            modelMap.addAttribute("id", id);
            return path;
        }
        return "thymeleaf/error";
    }

    @GetMapping("/logout")
    public String doLogout(
            HttpServletRequest request,
            HttpServletResponse response,
            ModelMap modelMap) {
        HttpSession session = request.getSession();
        if (Objects.nonNull(session.getAttribute("customerId"))) {
            session.removeAttribute("customerId");
            modelMap.addAttribute("loginState", false);
            return "thymeleaf/loginForm";
        }
        throw new LogoutException();

    }

}
