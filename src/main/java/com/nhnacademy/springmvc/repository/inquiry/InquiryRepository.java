package com.nhnacademy.springmvc.repository.inquiry;

import com.nhnacademy.springmvc.domain.Inquiry;
import com.nhnacademy.springmvc.domain.InquiryCategory;
import java.util.List;

public interface InquiryRepository {
    boolean exists(int inquiryId);

    Inquiry getInquiry(int inquiryId);

    Inquiry registerInquiry(String title, InquiryCategory inquiryCategory,
                            String postContent, String name, String customerId, String filePath);

    List<Inquiry> getInquiryList();

    List<Inquiry> matchInquiryList(String customerId);
}
