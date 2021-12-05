package com.meetio.quiz.service;

import com.meetio.quiz.dao.entity.UserSubmission;

import java.util.Date;
import java.util.List;

public interface UserService {
    UserSubmission saveAnswer(UserSubmission userSubmission);
    List<UserSubmission> generateUserStatistics(long userId);
    List<UserSubmission> generateOverallStatistics();
    List<UserSubmission> generateReportOfSpecificPeriod(Date startDate, Date endDate);

}
