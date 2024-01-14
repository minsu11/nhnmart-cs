package com.nhnacademy.springmvc.controller;

import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.nhnacademy.springmvc.domain.Customer;
import com.nhnacademy.springmvc.domain.Role;
import com.nhnacademy.springmvc.repository.customer.CustomerRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.util.NestedServletException;

class MainFormControllerTest {
    private MockMvc mockMvc;

    private CustomerRepository customerRepository;

    @BeforeEach
    void setUp() {
        customerRepository = mock(CustomerRepository.class);

        mockMvc = MockMvcBuilders.standaloneSetup(new MainFormController(customerRepository))
                .build();
    }

    @Test
    void viewCustomerRoleAdminTest() throws Exception {
        MockHttpSession mockSession = new MockHttpSession();
        Customer customer = Customer.create("test", "test1234");
        customer.setRole(Role.ADMIN);

        when(customerRepository.getCustomer("test")).thenReturn(customer);
        mockSession.setAttribute("customerId", "test");
        mockMvc.perform(get("/cs/").session(mockSession))
                .andExpect(status().isOk())
                .andExpect(view().name("thymeleaf/adminInquiryForm"));
    }

    @Test
    void viewCustomerRoleCustomerTest() throws Exception {
        MockHttpSession mockSession = new MockHttpSession();
        Customer customer = Customer.create("test1", "test12345");
        customer.setRole(Role.CUSTOMER);
        when(customerRepository.getCustomer("test")).thenReturn(customer);
        mockSession.setAttribute("customerId", "test");
        mockMvc.perform(get("/cs/").session(mockSession))
                .andExpect(status().isOk())
                .andExpect(view().name("thymeleaf/customerForm"));
    }

    @Test
    void sessionNullTest() throws Exception {
        MockHttpSession mockSession = new MockHttpSession();
        Customer customer = Customer.create("test1", "test12345");
        customer.setRole(Role.CUSTOMER);
        when(customerRepository.getCustomer("test")).thenReturn(customer);
        mockSession.setAttribute("customerId12", "test");
        Throwable th = catchThrowable(() ->
                mockMvc.perform(get("/cs/").session(mockSession))
                        .andExpect(status().isNotFound()));
        Assertions.assertThat(th).isInstanceOf(NestedServletException.class)
                .hasCauseInstanceOf(NullPointerException.class);

    }
}