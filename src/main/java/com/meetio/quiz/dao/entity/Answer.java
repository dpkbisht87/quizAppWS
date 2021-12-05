package com.meetio.quiz.dao.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Accessors(chain = true)
@Entity
@Table(name = "answer")
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;


    private String content;

    private long questionId;
}
