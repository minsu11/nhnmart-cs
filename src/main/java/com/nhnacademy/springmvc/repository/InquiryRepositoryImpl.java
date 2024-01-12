package com.nhnacademy.springmvc.repository;

import com.nhnacademy.springmvc.domain.Inquiry;
import com.nhnacademy.springmvc.domain.InquiryCategory;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class InquiryRepositoryImpl implements InquiryRepository {
    private List<Inquiry> inquiryList = new ArrayList<>();


    @Override
    public Inquiry getInquiry(int index) {
        if (index < 0 || inquiryList.size() < index) {
            throw new IndexOutOfBoundsException();
        }
        return inquiryList.get(index);
    }

    @Override
    public Inquiry registerInquiry(String title, InquiryCategory inquiryCategory,
                                   String postContent, Date date, String name) {
        Inquiry inquiry = Inquiry.create(title, inquiryCategory, postContent, date, name);
        inquiryList.add(inquiry);
        return inquiry;
    }

    @Override
    public List<Inquiry> getInquiryList() {
        return this.inquiryList;
    }
}
