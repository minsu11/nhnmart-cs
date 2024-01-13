package com.nhnacademy.springmvc.service;

import com.nhnacademy.springmvc.domain.Inquiry;
import com.nhnacademy.springmvc.repository.AnswerRepository;
import com.nhnacademy.springmvc.repository.InquiryRepository;
import org.springframework.stereotype.Service;

@Service
public class InquiryServiceImpl implements InquiryService {
    private InquiryRepository inquiryRepository;
    private AnswerRepository answerRepository;

    public InquiryServiceImpl(InquiryRepository inquiryRepository, AnswerRepository answerRepository) {
        this.inquiryRepository = inquiryRepository;
        this.answerRepository = answerRepository;
    }

    @Override
    public boolean register(Inquiry inquiry) {
        return false;
    }

    @Override
    public boolean answerCheck(String inquiryId) {
        if (answerRepository.existsAnswer(Integer.parseInt(inquiryId))) {

        }
        return false;
    }
}
