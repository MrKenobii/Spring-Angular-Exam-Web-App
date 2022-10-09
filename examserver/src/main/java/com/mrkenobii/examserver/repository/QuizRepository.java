package com.mrkenobii.examserver.repository;

import com.mrkenobii.examserver.model.exam.Category;
import com.mrkenobii.examserver.model.exam.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface QuizRepository extends JpaRepository<Quiz, Long> {
    public List<Quiz> findByCategory(Category category);
    public List<Quiz> findByActive(Boolean isActive);
    public List<Quiz> findByCategoryAndActive(Category category, Boolean isActive);
}
