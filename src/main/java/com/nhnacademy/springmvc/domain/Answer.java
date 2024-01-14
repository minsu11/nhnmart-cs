package com.nhnacademy.springmvc.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Answer {
    private int inquiryId;
    private String adminId;
    private String title;
    private String content;
    private String date;

    private Answer(int inquiryId, String title, String adminId, String content, String date) {
        this.inquiryId = inquiryId;
        this.title = title;
        this.adminId = adminId;
        this.content = content;
        this.date = date;
    }

    public static Answer create(int inquiryId, String title, String adminId, String content, String date) {
        return new Answer(inquiryId, title, adminId, content, date);
    }

}
