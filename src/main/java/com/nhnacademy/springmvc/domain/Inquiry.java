package com.nhnacademy.springmvc.domain;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Inquiry {
    private long id;
    private InquiryCategory inquiryCategory;

    private String postContent;
    private Date date;

    private Inquiry(long id, InquiryCategory inquiryCategory, String postContent, Date date) {
        this.id = id;
        this.inquiryCategory = inquiryCategory;
        this.postContent = postContent;
        this.date = date;
    }

    public Inquiry create(long id, InquiryCategory inquiryCategory, String postContent, Date date) {
        return new Inquiry(id, inquiryCategory, postContent, date);
    }

}
