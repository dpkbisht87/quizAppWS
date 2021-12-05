package com.meetio.quiz.quizAppWS;

import com.meetio.quiz.dao.entity.Answer;
import com.meetio.quiz.dao.entity.Question;
import com.meetio.quiz.dao.entity.QuestionStatus;
import com.meetio.quiz.dao.entity.UserSubmission;
import com.meetio.quiz.dao.repository.AnswerRepository;
import com.meetio.quiz.dao.repository.QuestionRepository;
import com.meetio.quiz.dao.repository.SolutionRepository;
import com.meetio.quiz.dao.repository.UserSubmissionRepository;
import com.meetio.quiz.model.Quiz;
import com.meetio.quiz.service.QuizServiceImpl;
import com.meetio.quiz.service.UserServiceImpl;
import com.meetio.quiz.utils.ResourceUtils;
import org.h2.engine.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class QuizServiceTest {

    @Mock
    private QuestionRepository questionRepository;
    @Mock
    private AnswerRepository answerRepository;
    @Mock
    private SolutionRepository solutionRepository;
    @Mock
    private UserSubmissionRepository userSubmissionRepository;

    @InjectMocks
    QuizServiceImpl quizService;

    @InjectMocks
    UserServiceImpl userService;

    @BeforeEach
    void initUseCase() {
        quizService = new QuizServiceImpl(questionRepository, answerRepository, solutionRepository);
        userService = new UserServiceImpl(userSubmissionRepository);
    }

    @Test
    public void saveAnswerSuccess(){
        UserSubmission userSubmission = new UserSubmission()
                .setSubmissionDate(new Date())
                .setUserId(1234L)
                .setQuestionId(1L)
                .setSubmittedAnswerId(2L)
                .setStatus(QuestionStatus.CORRECT);

        when(userSubmissionRepository.save(any(UserSubmission.class))).thenReturn(userSubmission);

        UserSubmission savedUserSubmission = userService.saveAnswer(userSubmission);
        Assertions.assertEquals(savedUserSubmission.getUserId(), 1234L);

    }
}
