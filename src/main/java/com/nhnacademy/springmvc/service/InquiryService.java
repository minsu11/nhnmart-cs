package com.nhnacademy.springmvc.service;

import com.nhnacademy.springmvc.domain.Answer;
import com.nhnacademy.springmvc.domain.Inquiry;
import com.nhnacademy.springmvc.domain.InquiryCategory;
import java.util.List;

public interface InquiryService {
    boolean registerInquiry(String title, InquiryCategory inquiryCategory,
                            String postContent, String customerName, String customerId, String filePath);

    boolean answerCheck(String inquiryId);

    List<Inquiry> getNotAnswerInquiryList();

    boolean registerAnswer(String inquiryId, String adminId, String content);

    Inquiry getInquiry(String inquiryId);

    Answer getAnswer(String inquiryId);
}
