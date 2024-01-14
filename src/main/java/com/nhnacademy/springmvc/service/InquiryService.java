package com.nhnacademy.springmvc.service;

import com.nhnacademy.springmvc.domain.Answer;
import com.nhnacademy.springmvc.domain.Inquiry;
import java.util.List;

public interface InquiryService {
    boolean register(Inquiry inquiry);

    boolean answerCheck(String inquiryId);

    List<Inquiry> getNotAnswerInquiryList();

    boolean registerAnswer(Answer answer);
}
