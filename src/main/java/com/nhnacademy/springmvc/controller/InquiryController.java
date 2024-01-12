package com.nhnacademy.springmvc.controller;

import com.nhnacademy.springmvc.repository.InquiryRepository;
import org.springframework.stereotype.Controller;

@Controller
public class InquiryController {
    private InquiryRepository inquiryRepository;

    public InquiryController(InquiryRepository inquiryRepository) {
        this.inquiryRepository = inquiryRepository;
    }
}
