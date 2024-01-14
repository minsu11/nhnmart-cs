package com.nhnacademy.springmvc.controller;

import com.nhnacademy.springmvc.domain.Customer;
import com.nhnacademy.springmvc.domain.InquiryCategory;
import com.nhnacademy.springmvc.domain.InquiryRegisterRequest;
import com.nhnacademy.springmvc.exception.ValidationFailedException;
import com.nhnacademy.springmvc.repository.customer.CustomerRepository;
import com.nhnacademy.springmvc.service.InquiryService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/cs")
public class CustomerInquiryRegisterController {
    private InquiryService inquiryService;
    private CustomerRepository customerRepository;
    private static final String UPLOAD_DIR = "/Users/minsu/Downloads/";


    public CustomerInquiryRegisterController(InquiryService inquiryService, CustomerRepository customerRepository) {
        this.inquiryService = inquiryService;
        this.customerRepository = customerRepository;
    }


    @GetMapping("/inquiry")
    public String viewRegisterInquiryForm() {
        return "thymeleaf/inquiryForm";
    }

    @PostMapping("/inquiry")
    public String registerInquiry(@Valid @ModelAttribute InquiryRegisterRequest inquiryRegisterRequest,
                                  @RequestParam("file") MultipartFile file,
                                  HttpServletRequest request,
                                  HttpServletResponse response,
                                  BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }
        HttpSession session = request.getSession();
        String filePath = UPLOAD_DIR + file.getOriginalFilename();
        String id = (String) session.getAttribute("customerId");
        Customer customer = customerRepository.getCustomer(id);
        for (InquiryCategory inquiryCategory : InquiryCategory.values()) {
            if (inquiryCategory.getValue().equals(inquiryRegisterRequest.getInquiryCategory())) {
                inquiryService.registerInquiry(inquiryRegisterRequest.getTitle(), inquiryCategory,
                        inquiryRegisterRequest.getContent(), customer.getName(), customer.getId(), filePath);
            }
        }
        return "thymeleaf/customerForm";
    }
}
