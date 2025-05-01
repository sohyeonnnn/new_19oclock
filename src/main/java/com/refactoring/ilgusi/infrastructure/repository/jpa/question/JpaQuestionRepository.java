package com.refactoring.ilgusi.infrastructure.repository.jpa.question;

import com.refactoring.ilgusi.domain.question.interfaces.QuestionRepository;

public class JpaQuestionRepository implements QuestionRepository {
    private final SpringDataQuestionRepository jpaRepository;

    public JpaQuestionRepository(SpringDataQuestionRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

}
