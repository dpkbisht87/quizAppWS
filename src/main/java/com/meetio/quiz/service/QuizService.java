package com.meetio.quiz.service;

import com.meetio.quiz.dao.entity.Question;
import com.meetio.quiz.dao.entity.UserSubmission;
import com.meetio.quiz.model.Quiz;

import java.util.List;

public interface QuizService {
    List<Quiz> loadQuiz();
    Quiz addNewQuiz(Quiz quiz);
    Quiz deleteQuiz(Quiz quiz);
    Quiz updateQuiz(Quiz quiz);
}
