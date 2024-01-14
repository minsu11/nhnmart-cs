package com.nhnacademy.springmvc.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import com.nhnacademy.springmvc.domain.*;
import com.nhnacademy.springmvc.repository.answer.AnswerRepository;
import com.nhnacademy.springmvc.repository.customer.CustomerRepository;
import com.nhnacademy.springmvc.repository.customer.CustomerRepositoryImpl;
import com.nhnacademy.springmvc.repository.inquiry.InquiryRepository;
import com.nhnacademy.springmvc.service.InquiryService;
import com.nhnacademy.springmvc.service.InquiryServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.ModelMap;

class CustomerMyInquiryDetailControllerTest {
    private InquiryService inquiryService;
    private CustomerRepository customerRepository;
    private MockMvc mockMvc;
    private CustomerMyInquiryDetailController controller;

    @BeforeEach
    void setUp() {
        InquiryRepository inquiryRepository = mock(InquiryRepository.class);
        AnswerRepository answerRepository = mock(AnswerRepository.class);
        inquiryService = new InquiryServiceImpl(inquiryRepository, answerRepository);
        customerRepository = new CustomerRepositoryImpl();
        controller = new CustomerMyInquiryDetailController(inquiryService, customerRepository);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

    }

    @Test
    void viewInquiryForm() {
        Inquiry inquiry = Inquiry.create(1, "test", InquiryCategory.OTHER_INQUIRIES, "test", "testName", "testId");
        Answer answer = Answer.create(1, "admin", "test", "2024-01-01");
        Customer admin = Customer.create("admin", "1234");
        admin.setRole(Role.ADMIN);

        when(inquiryService.getInquiry("1")).thenReturn(inquiry);
        when(inquiryService.getAnswer("1")).thenReturn(answer);
        when(customerRepository.getCustomer(answer.getAdminId())).thenReturn(admin);

        ModelMap modelMap = new ModelMap();
        String viewName = controller.viewInquiryForm("1", modelMap);

        verify(inquiryService, times(1)).getInquiry("1");
        verify(inquiryService, times(1)).getAnswer("1");
        verify(customerRepository, times(1)).getCustomer(answer.getAdminId());

        // 결과 확인
        assertThat(viewName).isEqualTo("thymeleaf/inquiryDetailForm");
        assertThat(modelMap).containsKeys("inquiry", "answer", "admin");
        assertThat(modelMap.get("inquiry")).isEqualTo(inquiry);
        assertThat(modelMap.get("answer")).isEqualTo(answer);
        assertThat(modelMap.get("admin")).isEqualTo(admin);
    }
}