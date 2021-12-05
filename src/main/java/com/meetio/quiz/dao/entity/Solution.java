package com.meetio.quiz.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@ToString
@Accessors(chain = true)
@Entity
@Table(name = "solution")
@IdClass(SolutionId.class)
@AllArgsConstructor
public class Solution {

    public Solution() {
    }

    @Id
    private long questionId;

    @Id
    private long answerId;

}
