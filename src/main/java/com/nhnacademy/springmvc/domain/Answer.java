package com.nhnacademy.springmvc.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Answer {
    private int inquiryId;
    private String adminId;
    private String content;
    private String date;

    private Answer(int inquiryId, String adminId, String content, String date) {
        this.adminId = adminId;
        this.content = content;
        this.date = date;
    }

    public static Answer create(int inquiryId, String adminId, String content, String date) {
        return new Answer(inquiryId, adminId, content, date);
    }

}
