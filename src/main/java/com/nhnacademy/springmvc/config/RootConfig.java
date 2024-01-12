package com.nhnacademy.springmvc.config;

import com.nhnacademy.springmvc.Base;
import com.nhnacademy.springmvc.domain.Role;
import com.nhnacademy.springmvc.repository.CustomerRepository;
import com.nhnacademy.springmvc.repository.CustomerRepositoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;

@Configuration
@ComponentScan(basePackageClasses = Base.class,
        excludeFilters = {@ComponentScan.Filter(Controller.class)})
public class RootConfig {
    @Bean
    public CustomerRepository customerRepository() {
        CustomerRepository customerRepository = new CustomerRepositoryImpl();
        customerRepository.register("admin", "1234", "admin", Role.ADMIN);
        customerRepository.register("customer1", "1234", "customer1", Role.CUSTOMER);
        customerRepository.register("admin1", "1234", "admin", Role.ADMIN);
        customerRepository.register("admin2", "1234", "admin", Role.ADMIN);
        customerRepository.register("admin3", "1234", "admin", Role.ADMIN);

        return customerRepository;

    }

}
