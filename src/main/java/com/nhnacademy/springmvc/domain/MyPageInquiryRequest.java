package com.nhnacademy.springmvc.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MyPageInquiryRequest {
    private String title;
    private InquiryCategory inquiryCategory;
    private String date;
    private boolean answer;
}
