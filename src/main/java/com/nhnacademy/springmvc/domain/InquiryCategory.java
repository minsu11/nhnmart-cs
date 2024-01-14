package com.nhnacademy.springmvc.domain;

public enum InquiryCategory {
    COMPLAINT("불만 접수"), PROPOSAL("제안"), REFUND_EXCHANGE("환불 교환"), COMPLIMENT("칭찬해요"), OTHER_INQUIRIES("기타 문의");
    private String value;

    InquiryCategory(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
