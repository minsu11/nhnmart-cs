package com.nhnacademy.springmvc.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Inquiry {
    private String title;
    private InquiryCategory inquiryCategory;
    private String postContent;
    private String date;
    private String customerName;
    private String customerId;
    private boolean answerCheck;


    private Inquiry(String title, InquiryCategory inquiryCategory, String postContent, String date, String customerName, String customerId) {
        this.title = title;
        this.inquiryCategory = inquiryCategory;
        this.postContent = postContent;
        this.date = date;
        this.customerName = customerName;
        this.customerId = customerId;
        this.answerCheck = false;
    }

    public static Inquiry create(String title, InquiryCategory inquiryCategory, String postContent, String date, String customerName, String customerId) {
        return new Inquiry(title, inquiryCategory, postContent, date, customerName, customerId);
    }

}
