package com.nhnacademy.springmvc.repository;

import com.nhnacademy.springmvc.domain.Inquiry;

public interface InquiryRepository {
    Inquiry registerInquiry(String content);
}
