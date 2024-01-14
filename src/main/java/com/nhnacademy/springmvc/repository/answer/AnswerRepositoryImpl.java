package com.nhnacademy.springmvc.repository.answer;

import com.nhnacademy.springmvc.domain.Answer;
import java.util.HashMap;
import java.util.Map;

public class AnswerRepositoryImpl implements AnswerRepository {
    private Map<Integer, Answer> answerMap = new HashMap<>();

    @Override
    public boolean existsAnswer(int inquiryId) { // 문의글이 있는 지 없는지
        return answerMap.containsKey(inquiryId);
    }

    @Override
    public Answer registerAnswer(int inquiryId, String adminId, String content, String date) {
        Answer answer = Answer.create(inquiryId, adminId, content, date);
        answerMap.put(inquiryId, answer);
        return answer;
    }

    @Override
    public Map<Integer, Answer> getAnserMap() {
        return answerMap;
    }


}
