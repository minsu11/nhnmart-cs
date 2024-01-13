package com.nhnacademy.springmvc.controller;

import com.nhnacademy.springmvc.repository.InquiryRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cs")
public class RegisterInquiryController {
    private InquiryRepository inquiryRepository;

    public RegisterInquiryController(InquiryRepository inquiryRepository) {
        this.inquiryRepository = inquiryRepository;
    }

    @GetMapping("/inquiry")
    public String viewRegisterInquiryForm() {

        return "thymeleaf/inquiryForm";
    }
}
