package com.meetio.quiz.controller.v1;

import com.meetio.quiz.controller.request.UserSubmissionRequest;
import com.meetio.quiz.dao.entity.UserSubmission;
import com.meetio.quiz.model.Quiz;
import com.meetio.quiz.service.QuizService;
import com.meetio.quiz.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import java.util.List;

@RestController
@RequestMapping("/api/v1/quiz/")
@CrossOrigin(origins = "http://localhost:4200")
public class QuizController {
    private static final Logger LOGGER = LoggerFactory.getLogger(QuizController.class);

    @Autowired
    private final QuizService quizService;

    @Autowired
    private final UserService userService;

    public QuizController(QuizService quizService, UserService userService) {
        this.quizService = quizService;
        this.userService = userService;
    }

    @GetMapping("/questions")
    public List<Quiz> getAllQuestions(){
        LOGGER.info("Loading all questions from the database");
        List<Quiz> questionList = quizService.loadQuiz();
        LOGGER.info("Count of Questions: {}", questionList.size());
        return questionList;
    }

    @PostMapping("/save")
    public ResponseEntity<UserSubmission> saveAnswer(@RequestBody UserSubmissionRequest userSubmissionRequest){
        UserSubmission userSubmission = new UserSubmission()
                .setUserId(userSubmissionRequest.getUserId())
                .setQuestionId(userSubmissionRequest.getQuestionId())
                .setSubmittedAnswerId(userSubmissionRequest.getSubmittedAnswerId())
                .setStatus(userSubmissionRequest.getStatus())
                .setLifeLine(userSubmissionRequest.getLifeLine());

        LOGGER.info("Saving User's Answer {}", userSubmission);
        userSubmission = userService.saveAnswer(userSubmission);

        if (userSubmission != null) {
            LOGGER.info("User's answer saved successfully");
            return ResponseEntity.ok().body(userSubmission);
        } else {
            LOGGER.info("Failed to save User's answer");
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/statistics/{userId}")
    public ResponseEntity<List<UserSubmission>> generateUserStatistics(@PathVariable("userId")  @Digits(integer=5, fraction=0) @DecimalMin(value = "0", inclusive = false) long userId){
        LOGGER.info("Generating Statistics for user with id {}", userId);
        List<UserSubmission> userSubmissionList = userService.generateUserStatistics(userId);
        if (userSubmissionList != null) {
            return ResponseEntity.ok().body(userSubmissionList);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
