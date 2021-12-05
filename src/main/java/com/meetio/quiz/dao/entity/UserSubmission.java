package com.meetio.quiz.dao.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.hibernate.annotations.IndexColumn;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@ToString
@Entity
@Accessors(chain = true)
@Table(name = "userSubmission")
public class UserSubmission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long userId;

    @Column
    private Long questionId;

    @Column
    private Long submittedAnswerId;

    @Column
    private QuestionStatus status;

    @Column
    private LifeLine lifeLine;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date submissionDate;
}
