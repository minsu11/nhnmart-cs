package com.nhnacademy.springmvc.controller;

import com.nhnacademy.springmvc.domain.Inquiry;
import com.nhnacademy.springmvc.service.InquiryService;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cs")
public class AdminInquiryController {
    private InquiryService inquiryService;

    public AdminInquiryController(InquiryService inquiryService) {
        this.inquiryService = inquiryService;
    }

    @GetMapping("/admin")
    public String viewAdminInquiryForm(ModelMap modelMap) {
        List<Inquiry> inquiryList = inquiryService.getNotAnswerInquiryList();
        modelMap.addAttribute("inquiryList", inquiryList);

        return "thymeleaf/adminInquiryForm";
    }
}
