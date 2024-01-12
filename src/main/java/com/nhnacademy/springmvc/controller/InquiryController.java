package com.nhnacademy.springmvc.controller;

import com.nhnacademy.springmvc.repository.InquiryRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cs")
public class InquiryController {
    private InquiryRepository inquiryRepository;

    public InquiryController(InquiryRepository inquiryRepository) {
        this.inquiryRepository = inquiryRepository;
    }

    @GetMapping("/inquiry")
    public String viewInquiry() {
        return "thymeleaf/inquiryForm";
    }

    @PostMapping("/inquiry")
    public String registerInquiry() {
        
        return "thymeleaf/customerForm";
    }
}
