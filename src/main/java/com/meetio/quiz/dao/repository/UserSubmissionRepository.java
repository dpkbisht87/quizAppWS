package com.meetio.quiz.dao.repository;

import com.meetio.quiz.dao.entity.UserSubmission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface UserSubmissionRepository extends JpaRepository<UserSubmission, String> {
    List<UserSubmission> findByUserId(long userId);

    @Query("select a from UserSubmission a where a.submissionDate >= :startDate and a.submissionDate >= :endDate")
    List<UserSubmission> findAllSubmissionsInRange(@Param("startDate") Date startDate,
                                                   @Param("endDate") Date endDate);
}
