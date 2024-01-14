package com.nhnacademy.springmvc.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.nhnacademy.springmvc.repository.answer.AnswerRepository;
import com.nhnacademy.springmvc.repository.customer.CustomerRepository;
import com.nhnacademy.springmvc.repository.inquiry.InquiryRepository;
import com.nhnacademy.springmvc.service.InquiryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.multipart.MultipartFile;

class CustomerInquiryRegisterControllerTest {
    private InquiryService inquiryService;
    private CustomerRepository customerRepository;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        customerRepository = mock(CustomerRepository.class);
        InquiryRepository inquiryRepository = mock(InquiryRepository.class);
        AnswerRepository answerRepository = mock(AnswerRepository.class);
        inquiryService = mock(InquiryService.class);  // InquiryService를 mock으로 변경
        when(inquiryService.registerInquiry(any(), any(), any(), any(), any(), any())).thenReturn(true); // registerInquiry 호출 시 true 반환하도록 설정

        CustomerInquiryRegisterController customerInquiryRegisterController = new CustomerInquiryRegisterController(inquiryService, customerRepository);
        this.mockMvc = MockMvcBuilders.standaloneSetup(customerInquiryRegisterController).build();

    }

    @Test
    void viewRegisterInquiryForm() throws Exception {
        this.mockMvc.perform(get("/cs/inquiry"))
                .andExpect(status().isOk()).andExpect(view().name("thymeleaf/inquiryForm"));
    }

    @Test
    void registerInquiryTest() throws Exception {

        String title = "Test Inquiry";
        String category = "COMPLAINT";
        String content = "Test Content";
        String customerName = "Test Customer";
        String customerId = "test123";
        String filePath = "test-file-path";
        MultipartFile file = new MockMultipartFile("file", "test-file.txt", "text/plain", "test file content".getBytes());


        // 테스트 수행
        mockMvc.perform(MockMvcRequestBuilders.fileUpload("/cs/inquiry")
                        .file((MockMultipartFile) file)
                        .param("title", title)
                        .param("inquiryCategory", category)
                        .param("content", content)
                        .param("customerName", customerName)
                        .param("customerId", customerId)
                        .param("filePath", filePath)
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("thymeleaf/customerForm"));

    }


}