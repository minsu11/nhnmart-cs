package com.nhnacademy.springmvc.repository.answer;

import com.nhnacademy.springmvc.domain.Answer;
import java.util.Map;

public interface AnswerRepository {
    boolean existsAnswer(int inquiryId);

    Answer registerAnswer(int inquiryId, String adminId, String content, String date);

    Map<Integer, Answer> getAnserMap();
}
