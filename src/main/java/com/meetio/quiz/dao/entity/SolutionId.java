package com.meetio.quiz.dao.entity;

import javax.persistence.Id;
import java.io.Serializable;

public class SolutionId implements Serializable {
    private Long questionId;
    private Long answerId;
}
