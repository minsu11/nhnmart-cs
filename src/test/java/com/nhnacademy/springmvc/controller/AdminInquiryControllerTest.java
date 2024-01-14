package com.nhnacademy.springmvc.controller;


import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.nhnacademy.springmvc.repository.answer.AnswerRepository;
import com.nhnacademy.springmvc.repository.inquiry.InquiryRepository;
import com.nhnacademy.springmvc.service.InquiryService;
import com.nhnacademy.springmvc.service.InquiryServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

class AdminInquiryControllerTest {
    private InquiryService inquiryService;
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        InquiryRepository inquiryRepository = mock(InquiryRepository.class);
        AnswerRepository answerRepository = mock(AnswerRepository.class);

        inquiryService = new InquiryServiceImpl(inquiryRepository, answerRepository);
        AdminInquiryController adminInquiryController = new AdminInquiryController(inquiryService);
        this.mockMvc = MockMvcBuilders.standaloneSetup(adminInquiryController).build();
    }

    @Test
    void viewAdminInquiryForm() throws Exception {
//        List<Inquiry> inquiryList = new ArrayList<>();
//        when(inquiryService.getNotAnswerInquiryList()).thenReturn(inquiryList);
        this.mockMvc.perform(get("/cs/admin"))
                .andExpect(status().isOk())
                .andExpect(view().name("thymeleaf/adminInquiryForm"));
    }
}