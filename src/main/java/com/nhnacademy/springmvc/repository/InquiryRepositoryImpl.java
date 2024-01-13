package com.nhnacademy.springmvc.repository;

import com.nhnacademy.springmvc.domain.Inquiry;
import com.nhnacademy.springmvc.domain.InquiryCategory;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InquiryRepositoryImpl implements InquiryRepository {
    private List<Inquiry> inquiryList = new ArrayList<>();
    private int inquiryId = 1;

    private ThreadLocal<SimpleDateFormat> dateFormatThreadLocal = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd"));

    @Override
    public Inquiry getInquiry(int index) {
        if (index < 0 || inquiryList.size() < index) {
            throw new IndexOutOfBoundsException();
        }
        return inquiryList.get(index);
    }

    @Override
    public Inquiry registerInquiry(String title, InquiryCategory inquiryCategory,
                                   String postContent, String name, String customerId) {
        String date = dateFormatThreadLocal.get().format(new Date());
        Inquiry inquiry = Inquiry.create(inquiryId, title, inquiryCategory, postContent, date, name, customerId);
        inquiryList.add(inquiry);
        inquiryId += 1;
        return inquiry;
    }

    @Override
    public List<Inquiry> getInquiryList() {
        return this.inquiryList;
    }

    @Override
    public List<Inquiry> matchInquiryList(String customerId) {
        List<Inquiry> matchInquiryList = new ArrayList<>();
        for (Inquiry inquiry : inquiryList) {
            if (inquiry.getCustomerId().equals(customerId)) {
                matchInquiryList.add(inquiry);
            }
        }
        return matchInquiryList;
    }
}
