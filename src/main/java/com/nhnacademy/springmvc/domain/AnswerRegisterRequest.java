package com.nhnacademy.springmvc.domain;

import javax.validation.constraints.NotEmpty;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AnswerRegisterRequest {

    @NotEmpty
    @Length(min = 1, max = 40000)
    String content;

    String inquiryId;


}
