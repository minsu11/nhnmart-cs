package com.nhnacademy.springmvc.service;

import com.nhnacademy.springmvc.domain.Inquiry;
import com.nhnacademy.springmvc.repository.InquiryRepository;

public class InquiryServiceImpl implements InquiryService {
    private InquiryRepository inquiryRepository;

    @Override
    public boolean register(Inquiry inquiry) {
        return false;
    }
}
