package com.meetio.quiz.dao.repository;

import com.meetio.quiz.dao.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}
