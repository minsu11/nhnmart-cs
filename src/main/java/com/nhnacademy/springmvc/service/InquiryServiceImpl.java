package com.nhnacademy.springmvc.service;

import com.nhnacademy.springmvc.domain.Answer;
import com.nhnacademy.springmvc.domain.Inquiry;
import com.nhnacademy.springmvc.repository.answer.AnswerRepository;
import com.nhnacademy.springmvc.repository.inquiry.InquiryRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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
        if (Objects.isNull(inquiry)) {
            throw new NullPointerException();
        }
        inquiryRepository.registerInquiry(inquiry.getTitle(), inquiry.getInquiryCategory(),
                inquiry.getPostContent(), inquiry.getCustomerName(), inquiry.getCustomerId());

        return true;
    }

    @Override
    public boolean answerCheck(String inquiryId) {
        return answerRepository.existsAnswer(Integer.parseInt(inquiryId));
    }

    @Override
    public List<Inquiry> getNotAnswerInquiryList() {
        List<Inquiry> inquiryList = inquiryRepository.getInquiryList();
        List<Inquiry> notAnswerInquiryList = new ArrayList<>();
        for (Inquiry inquiry : inquiryList) {
            if (!inquiry.getAnswerCheck()) {
                notAnswerInquiryList.add(inquiry);
            }
        }
        return notAnswerInquiryList;
    }

    @Override
    public boolean registerAnswer(Answer answer) {
        if (Objects.isNull(answer)) {
            throw new NullPointerException();
        }
        answerRepository.registerAnswer(answer.getInquiryId(), answer.getTitle(),
                answer.getAdminId(), answer.getContent(), answer.getDate());
        inquiryRepository.getInquiry(answer.getInquiryId()).setAnswerCheck(true);

        return true;
    }
}
