package com.mrkenobii.examserver.repository;

import com.mrkenobii.examserver.model.exam.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizRepository extends JpaRepository<Quiz, Long> {
}
