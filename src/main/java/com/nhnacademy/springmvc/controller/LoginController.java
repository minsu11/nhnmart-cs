package com.nhnacademy.springmvc.controller;

import com.nhnacademy.springmvc.repository.CustomerRepository;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

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
    public String doLogin(@RequestParam("id") String id,
                          @RequestParam("password") String password,
                          HttpServletRequest request,
                          HttpServletResponse response,
                          ModelMap modelMap){
        if(customerRepository.matches(id,password)){
            HttpSession session = request.getSession();
            session.setAttribute("customerId",id);
            session.setMaxInactiveInterval(1800);
            modelMap.addAttribute("id", session);

        }


        return
    }

}
