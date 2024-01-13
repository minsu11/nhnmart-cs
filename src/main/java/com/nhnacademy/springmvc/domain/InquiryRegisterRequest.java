package com.nhnacademy.springmvc.domain;

import javax.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class InquiryRegisterRequest {
    @NotBlank
    @Length(min = 2, max = 200)
    String title;

    @NotBlank
    @Length(min = 0, max = 40_000)
    String content;


}
