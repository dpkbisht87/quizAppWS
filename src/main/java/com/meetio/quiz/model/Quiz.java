package com.meetio.quiz.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.meetio.quiz.dao.entity.Answer;
import com.meetio.quiz.dao.entity.Question;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class Quiz {

    public Quiz(Question question, List<Answer> optionList, Answer correctAnswer) {
        this.question = question;
        this.optionList = optionList;
        this.correctAnswer = correctAnswer;
    }
    public Quiz(){ }


    private Question question;
    private List<Answer> optionList;
    private Answer correctAnswer;
}
