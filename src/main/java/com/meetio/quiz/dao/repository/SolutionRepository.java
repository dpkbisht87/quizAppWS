package com.meetio.quiz.dao.repository;

import com.meetio.quiz.dao.entity.Answer;
import com.meetio.quiz.dao.entity.Solution;
import com.meetio.quiz.dao.entity.SolutionId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SolutionRepository extends JpaRepository<Solution, SolutionId> {
    Solution findByQuestionId(Long questionId);
}
