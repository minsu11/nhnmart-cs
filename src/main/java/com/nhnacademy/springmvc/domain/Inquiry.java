package com.nhnacademy.springmvc.domain;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Inquiry {
    private String title;
    private InquiryCategory inquiryCategory;
    private String postContent;
    private Date date;
    private String name;

    private Inquiry(String title, InquiryCategory inquiryCategory, String postContent, Date date, String name) {
        this.title = title;
        this.inquiryCategory = inquiryCategory;
        this.postContent = postContent;
        this.date = date;
        this.name = name;
    }

    public static Inquiry create(String title, InquiryCategory inquiryCategory, String postContent, Date date, String name) {
        return new Inquiry(title, inquiryCategory, postContent, date, name);
    }
}
