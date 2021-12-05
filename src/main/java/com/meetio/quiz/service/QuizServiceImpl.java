package com.meetio.quiz.service;

import com.meetio.quiz.dao.entity.Answer;
import com.meetio.quiz.dao.entity.Question;
import com.meetio.quiz.dao.entity.Solution;
import com.meetio.quiz.dao.repository.AnswerRepository;
import com.meetio.quiz.dao.repository.QuestionRepository;
import com.meetio.quiz.dao.repository.SolutionRepository;
import com.meetio.quiz.model.Quiz;
import com.meetio.quiz.utils.ResourceUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizServiceImpl implements  QuizService {
    private static final Logger LOGGER = LoggerFactory.getLogger(QuizServiceImpl.class);


    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;
    private final SolutionRepository solutionRepository;

    @Autowired
    ResourceUtils resourceUtils;

    @PostConstruct
    public void init() throws Exception {
        try {
            resourceUtils.seedQuiz();
        } catch (IOException | NullPointerException e) {
            LOGGER.error("Mock quiz data could not be initialised. ", e);
        }
    }

    public QuizServiceImpl(QuestionRepository questionRepository, AnswerRepository answerRepository,
                           SolutionRepository solutionRepository) {
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
        this.solutionRepository = solutionRepository;
    }

    @Override
    public List<Quiz> loadQuiz() {
        Quiz quiz;
        List<Question> questionList =  questionRepository.findAll();
        LOGGER.info("questionList:" + questionList);
        List <Quiz> quizList = new ArrayList<>();
        for (Question question: questionList){
            Long questionId = question.getId();
            List<Answer> optionList = answerRepository.findByQuestionId(questionId);
            Solution solution = solutionRepository.findByQuestionId(questionId);
            Optional<Answer> correctAnswer = answerRepository.findById(solution.getAnswerId());
            if (correctAnswer.isPresent()) {
                quiz = new Quiz(question, optionList, correctAnswer.get());
                quizList.add(quiz);
            }
        }
        return quizList;
    }

    @Override
    public Quiz addNewQuiz(Quiz quiz) {
        return null;
    }

    @Override
    public Quiz deleteQuiz(Quiz quiz) {
        return null;
    }

    @Override
    public Quiz updateQuiz(Quiz quiz) {
        return null;
    }


}
