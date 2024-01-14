package com.nhnacademy.springmvc.controller;

import com.nhnacademy.springmvc.domain.Inquiry;
import com.nhnacademy.springmvc.repository.inquiry.InquiryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/cs")
public class CustomerMyInquiryDetailController {
    private InquiryRepository inquiryRepository;

    public CustomerMyInquiryDetailController(InquiryRepository inquiryRepository) {
        this.inquiryRepository = inquiryRepository;
    }

    @GetMapping("/inquiry/detail/{inquiryId}")
    public String viewInquiryForm(@PathVariable String inquiryId,
                                  ModelMap modelMap) {
        Inquiry inquiry = inquiryRepository.getInquiry(Integer.parseInt(inquiryId));
        modelMap.addAttribute("inquiry", inquiry);
        return "thymeleaf/inquiryDetailForm";
    }
}
