package com.refactoring.ilgusi.infrastructure.repository.jpa.question;

import com.refactoring.ilgusi.domain.question.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataQuestionRepository extends JpaRepository<Question, Integer> {
}
