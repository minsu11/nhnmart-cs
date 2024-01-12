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
    private String name;

    private Inquiry(String title, InquiryCategory inquiryCategory, String postContent, String date, String name) {
        this.title = title;
        this.inquiryCategory = inquiryCategory;
        this.postContent = postContent;
        this.date = date;
        this.name = name;
    }

    public static Inquiry create(String title, InquiryCategory inquiryCategory, String postContent, String date, String name) {
        return new Inquiry(title, inquiryCategory, postContent, date, name);
    }
}
