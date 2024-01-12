package com.nhnacademy.springmvc.repository;

import com.nhnacademy.springmvc.domain.Inquiry;
import com.nhnacademy.springmvc.domain.InquiryCategory;
import java.util.List;

public interface InquiryRepository {

    Inquiry getInquiry(int inquiryId);

    Inquiry registerInquiry(String title, InquiryCategory inquiryCategory,
                            String postContent, String name);

    List<Inquiry> getInquiryList();
}
