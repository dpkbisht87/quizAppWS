package com.meetio.quiz.service;

import com.meetio.quiz.dao.entity.UserSubmission;
import com.meetio.quiz.dao.repository.UserSubmissionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserSubmissionRepository userSubmissionRepository;

    public UserServiceImpl(UserSubmissionRepository userSubmissionRepository) {
        this.userSubmissionRepository = userSubmissionRepository;
    }

    @Override
    public UserSubmission saveAnswer(UserSubmission userSubmission){
        userSubmission.setSubmissionDate(new Date());
        return userSubmissionRepository.save(userSubmission);
    }

    @Override
    public List<UserSubmission> generateUserStatistics(long userId){
        return userSubmissionRepository.findByUserId(userId);
    }

    @Override
    public List<UserSubmission> generateOverallStatistics() {
        return userSubmissionRepository.findAll();
    }

    @Override
    public List<UserSubmission> generateReportOfSpecificPeriod(Date startDate, Date endDate) {
        return userSubmissionRepository.findAllSubmissionsInRange(startDate, endDate);
    }

}
