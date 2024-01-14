package com.nhnacademy.springmvc.controller;

import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.nhnacademy.springmvc.domain.AnswerRegisterRequest;
import com.nhnacademy.springmvc.domain.Inquiry;
import com.nhnacademy.springmvc.domain.InquiryCategory;
import com.nhnacademy.springmvc.exception.ValidationFailedException;
import com.nhnacademy.springmvc.service.InquiryService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.util.NestedServletException;

class AdminAnswerRegisterControllerTest {
    private InquiryService inquiryService;
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        inquiryService = mock(InquiryService.class);
        AdminAnswerRegisterController adminAnswerRegisterController = new AdminAnswerRegisterController(inquiryService);
        this.mockMvc = MockMvcBuilders.standaloneSetup(adminAnswerRegisterController).build();
    }

    @Test
    void viewAnswerForm() throws Exception {
        String inquiryId = "0";
        Inquiry inquiry = Inquiry.create(0, "test", InquiryCategory.OTHER_INQUIRIES, "test", "test", "test1");
        when(inquiryService.getInquiry(inquiryId)).thenReturn(inquiry);
        this.mockMvc.perform(get("/cs/admin/answer/0")
                        .param("inquiryId", "0"))
                .andExpect(view().name("thymeleaf/adminAnswerRegisterForm"));

    }

    @Test
    void registerAnswerTest() throws Exception {
        String inquiryId = "0";
        MockHttpSession mockSession = new MockHttpSession();
        mockSession.setAttribute("customerId", "test");
        when(inquiryService.registerAnswer(inquiryId, "test", "test content")).thenReturn(true);
        mockMvc.perform(post("/cs/admin/answer")
                        .session(mockSession)
                        .param("inquiryId", "0")
                        .param("content", "asda"))
                .andExpect(status().isOk())
                .andExpect(view().name("thymeleaf/adminForm"));

    }

    @Test
    void registerAnswerValidationTest() throws Exception {

        String inquiryId = "0";
        MockHttpSession mockSession = new MockHttpSession();
        mockSession.setAttribute("customerId", "test");
        AnswerRegisterRequest answerRegisterRequest = new AnswerRegisterRequest("", "1");
        when(inquiryService.registerAnswer(inquiryId, "test", answerRegisterRequest.getContent())).thenReturn(true);

        Throwable th = catchThrowable(() ->
                mockMvc.perform(post("/cs/admin/answer")
                                .session(mockSession)
                                .param("inquiryId", "0")
                                .param("content", "")
                        ).andExpect(status().isBadRequest())
                        .andDo(print()));
        Assertions.assertThat(th).isInstanceOf(NestedServletException.class)
                .hasCauseInstanceOf(ValidationFailedException.class);

    }

    @Test
    void registerAnswerNullTest() throws Exception {

        String inquiryId = "0";
        MockHttpSession mockSession = new MockHttpSession();
        mockSession.setAttribute("customerId3", "test3");

        when(inquiryService.registerAnswer(inquiryId, "test", "")).thenReturn(true);

        Throwable th = catchThrowable(() ->
                mockMvc.perform(post("/cs/admin/answer")
                        .session(mockSession)
                        .param("inquiryId", "0")
                        .param("content", "das")
                ));
        Assertions.assertThat(th).isInstanceOf(NestedServletException.class)
                .hasCauseInstanceOf(NullPointerException.class);

    }

}