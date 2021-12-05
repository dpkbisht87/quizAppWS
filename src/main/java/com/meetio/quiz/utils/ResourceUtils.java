package com.meetio.quiz.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.meetio.quiz.dao.entity.Answer;
import com.meetio.quiz.dao.entity.Question;
import com.meetio.quiz.dao.entity.Solution;
import com.meetio.quiz.dao.repository.AnswerRepository;
import com.meetio.quiz.dao.repository.QuestionRepository;
import com.meetio.quiz.dao.repository.SolutionRepository;
import com.meetio.quiz.model.Quiz;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Service
public class ResourceUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(ResourceUtils.class);

    @Autowired
    ResourceLoader resourceLoader;

    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;
    private final SolutionRepository solutionRepository;
    private static List<Quiz> quizList;

    public ResourceUtils(QuestionRepository questionRepository, AnswerRepository answerRepository, SolutionRepository solutionRepository) {
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
        this.solutionRepository = solutionRepository;
    }

    public void seedQuiz() throws Exception {
        Resource resource = resourceLoader.getResource("classpath:data/mockdata.json");
        File jsonFile = resource.getFile();
        final ObjectMapper objectMapper = new ObjectMapper();
        quizList = objectMapper.readValue(jsonFile, new TypeReference<List<Quiz>>() {});
        LOGGER.info("Seeding same quiz from mockdata.json");
        Question mockQuestion = null;
        List<Answer> mockOptions = null;
        Solution mockSolution = null;
        for(Quiz mockQuiz : quizList){
            LOGGER.debug("Mock Quiz : {}",  mockQuiz );
            mockQuestion = questionRepository.save(mockQuiz.getQuestion());
            mockOptions = answerRepository.saveAll(mockQuiz.getOptionList());
            mockSolution = solutionRepository.save(new Solution(mockQuiz.getQuestion().getId(),
                    mockQuiz.getCorrectAnswer().getId()));
            if (mockQuestion == null || mockOptions == null || mockSolution == null){
                throw new Exception("Seeding failed");
            }
        }
    }
}
