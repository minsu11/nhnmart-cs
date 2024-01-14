package com.nhnacademy.springmvc.controller;

import com.nhnacademy.springmvc.domain.Answer;
import com.nhnacademy.springmvc.domain.Customer;
import com.nhnacademy.springmvc.domain.Inquiry;
import com.nhnacademy.springmvc.repository.customer.CustomerRepository;
import com.nhnacademy.springmvc.service.InquiryService;
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
    private InquiryService inquiryService;
    private CustomerRepository customerRepository;

    public CustomerMyInquiryDetailController(InquiryService inquiryService, CustomerRepository customerRepository) {
        this.inquiryService = inquiryService;
        this.customerRepository = customerRepository;
    }

    @GetMapping("/inquiry/detail/{inquiryId}")
    public String viewInquiryForm(@PathVariable("inquiryId") String inquiryId,
                                  ModelMap modelMap) {
        Inquiry inquiry = inquiryService.getInquiry(inquiryId);
        if (inquiry.isAnswerCheck()) {
            Answer answer = inquiryService.getAnswer(inquiryId);
            Customer admin = customerRepository.getCustomer(answer.getAdminId());
            modelMap.addAttribute("answer", inquiryService.getAnswer(inquiryId));
            modelMap.addAttribute("admin", admin);
        }
        modelMap.addAttribute("inquiry", inquiry);
        return "thymeleaf/inquiryDetailForm";
    }
}
