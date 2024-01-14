package com.nhnacademy.springmvc.controller;

import com.nhnacademy.springmvc.domain.AnswerRegisterRequest;
import com.nhnacademy.springmvc.domain.Inquiry;
import com.nhnacademy.springmvc.exception.ValidationFailedException;
import com.nhnacademy.springmvc.service.InquiryService;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cs/admin")
public class AdminAnswerRegisterController {
    private InquiryService inquiryService;

    public AdminAnswerRegisterController(InquiryService inquiryService) {
        this.inquiryService = inquiryService;
    }

    @GetMapping("/answer/{inquiryId}")
    public String viewAnswerForm(@PathVariable String inquiryId,
                                 ModelMap modelMap) {
        Inquiry inquiry = inquiryService.getInquiry(inquiryId);
        modelMap.addAttribute("inquiry", inquiry);

        return "thymeleaf/adminAnswerRegisterForm";
    }

    @PostMapping("/answer")
    public String registerAnswer(@Valid @ModelAttribute AnswerRegisterRequest answerRegisterRequest,
                                 HttpServletRequest request,
                                 HttpServletResponse response,
                                 BindingResult bindingResult,
                                 ModelMap modelMap) {
        if (bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }
        String inquiryId = answerRegisterRequest.getInquiryId();
        HttpSession session = request.getSession();
        if (Objects.isNull(session.getAttribute("customerId"))) {
            throw new NullPointerException();
        }
        String id = (String) session.getAttribute("customerId");
        inquiryService.registerAnswer(inquiryId, id, answerRegisterRequest.getContent());

        return "thymeleaf/adminForm";
    }
}
