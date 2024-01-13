package com.nhnacademy.springmvc.service;

import com.nhnacademy.springmvc.domain.Inquiry;

public interface InquiryService {
    boolean register(Inquiry inquiry);

    boolean answerCheck(String inquiryId);
}
