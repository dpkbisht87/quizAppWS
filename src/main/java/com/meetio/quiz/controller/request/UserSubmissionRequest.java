package com.meetio.quiz.controller.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.meetio.quiz.dao.entity.LifeLine;
import com.meetio.quiz.dao.entity.QuestionStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserSubmissionRequest {
    @NotEmpty(message = "UserId cannot be empty")
    private Long userId;

    @NotEmpty(message = "QuestionId cannot be empty")
    private Long questionId;

    @NotEmpty(message = "SubmittedAnswerId cannot be empty")
    private Long submittedAnswerId;

    @NotEmpty(message = "submissionStatus cannot be empty")
    private QuestionStatus status;

    private LifeLine lifeLine;
}
