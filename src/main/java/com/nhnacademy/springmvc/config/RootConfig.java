package com.nhnacademy.springmvc.config;

import com.nhnacademy.springmvc.Base;
import com.nhnacademy.springmvc.domain.Answer;
import com.nhnacademy.springmvc.domain.InquiryCategory;
import com.nhnacademy.springmvc.domain.Role;
import com.nhnacademy.springmvc.repository.answer.AnswerRepository;
import com.nhnacademy.springmvc.repository.answer.AnswerRepositoryImpl;
import com.nhnacademy.springmvc.repository.customer.CustomerRepository;
import com.nhnacademy.springmvc.repository.customer.CustomerRepositoryImpl;
import com.nhnacademy.springmvc.repository.inquiry.InquiryRepository;
import com.nhnacademy.springmvc.repository.inquiry.InquiryRepositoryImpl;
import com.nhnacademy.springmvc.service.InquiryService;
import com.nhnacademy.springmvc.service.InquiryServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;

@Configuration
@ComponentScan(basePackageClasses = Base.class,
        excludeFilters = {@ComponentScan.Filter(Controller.class)})
public class RootConfig {
    @Bean
    public CustomerRepository customerRepository() {
        CustomerRepository customerRepository = new CustomerRepositoryImpl();
        customerRepository.register("admin", "1234", "admin", Role.ADMIN);
        customerRepository.register("customer1", "1234", "customer1", Role.CUSTOMER);
        customerRepository.register("admin1", "1234", "admin", Role.ADMIN);
        customerRepository.register("admin2", "1234", "admin", Role.ADMIN);
        customerRepository.register("admin3", "1234", "admin", Role.ADMIN);

        return customerRepository;

    }

    @Bean
    public InquiryRepository inquiryRepository() {
        InquiryRepository inquiryRepository = new InquiryRepositoryImpl();
        inquiryRepository.registerInquiry("c1", InquiryCategory.OTHER_INQUIRIES, "내용1", "customer1", "customer1");
        inquiryRepository.registerInquiry("제목2", InquiryCategory.COMPLIMENT, "내용2", "customer1", "customer1");
        inquiryRepository.registerInquiry("제목3", InquiryCategory.REFUND_EXCHANGE, "내용3", "customer1", "customer1");
        inquiryRepository.registerInquiry("제목4", InquiryCategory.PROPOSAL, "내용4", "customer1", "customer1");
        return inquiryRepository;
    }

    @Bean
    public AnswerRepository answerRepository() {
        AnswerRepository answerRepository = new AnswerRepositoryImpl();

        return answerRepository;

    }

    @Bean
    public InquiryService inquiryService() {
        InquiryService inquiryService = new InquiryServiceImpl(inquiryRepository(), answerRepository());
        Answer answer = Answer.create(1, "답변 1", "admin", "내용", "2024-01-13");
        inquiryService.registerAnswer(answer);
        return inquiryService;
    }

}
