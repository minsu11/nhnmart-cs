package com.nhnacademy.springmvc.service;

import com.nhnacademy.springmvc.domain.Answer;
import com.nhnacademy.springmvc.domain.Inquiry;
import com.nhnacademy.springmvc.domain.InquiryCategory;
import com.nhnacademy.springmvc.repository.answer.AnswerRepository;
import com.nhnacademy.springmvc.repository.inquiry.InquiryRepository;
import java.util.ArrayList;
import java.util.List;
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
    public boolean registerInquiry(String title, InquiryCategory inquiryCategory, String postContent, String customerName, String customerId, String filePath) {

        inquiryRepository.registerInquiry(title, inquiryCategory, postContent, customerName, customerId, filePath);

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
        for (int i = inquiryList.size() - 1; i >= 0; i--) {
            if (!inquiryList.get(i).isAnswerCheck()) {
                notAnswerInquiryList.add(inquiryList.get(i));
            }
        }
        return notAnswerInquiryList;
    }


    @Override
    public boolean registerAnswer(String inquiryId, String adminId, String content) {

        answerRepository.registerAnswer(Integer.parseInt(inquiryId), adminId, content);
        inquiryRepository.getInquiry(Integer.parseInt(inquiryId)).setAnswerCheck(true);

        return true;
    }

    @Override
    public Inquiry getInquiry(String inquiryId) {
        return inquiryRepository.getInquiry(Integer.parseInt(inquiryId));
    }

    @Override
    public Answer getAnswer(String inquiryId) {
        return answerRepository.getAnswer(Integer.parseInt(inquiryId));
    }
}
