package com.nhnacademy.springmvc.controller;

import com.nhnacademy.springmvc.domain.Inquiry;
import com.nhnacademy.springmvc.repository.inquiry.InquiryRepository;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/cs")
public class CustomerMyInquiryPageController { // 문의 글 등록
    private InquiryRepository inquiryRepository;

    public CustomerMyInquiryPageController(InquiryRepository inquiryRepository) {
        this.inquiryRepository = inquiryRepository;
    }


    @GetMapping("/myInquiry")
    public String viewMyInquiryPage(HttpServletRequest request,
                                    HttpServletResponse response,
                                    ModelMap modelMap) {
        HttpSession session = request.getSession();
        String customerId = (String) session.getAttribute("customerId");
        List<Inquiry> inquiryList = inquiryRepository.matchInquiryList(customerId);
        modelMap.addAttribute("inquiryList", inquiryList);

        return "thymeleaf/myInquiryForm";
    }

}
