package com.mrkenobii.examserver.service;

import com.mrkenobii.examserver.model.exam.Category;
import com.mrkenobii.examserver.model.exam.Quiz;
import com.mrkenobii.examserver.repository.CategoryRepository;
import com.mrkenobii.examserver.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Service
public class QuizService {
    private final QuizRepository quizRepository;
    private final CategoryRepository categoryRepository;
    @Autowired
    public QuizService(QuizRepository quizRepository, CategoryRepository categoryRepository) {
        this.quizRepository = quizRepository;
        this.categoryRepository = categoryRepository;
    }
    public Quiz addQuiz(Quiz quiz){
        return quizRepository.save(quiz);
    }
    public Quiz updateQuiz(Quiz quiz){
        Quiz quiz1 = quizRepository.findById(quiz.getId()).orElseThrow(RuntimeException::new);
        quiz1.setDescription(quiz.getDescription());
        quiz1.setTitle(quiz.getTitle());
        quiz1.setCategory(quiz.getCategory());
        quiz1.setActive(quiz.isActive());
        quiz1.setQuestions(quiz.getQuestions());
        quiz1.setMaxMarks(quiz.getMaxMarks());
        quiz1.setNumberOfQuestions(quiz.getNumberOfQuestions());
        return quizRepository.save(quiz1);
    }
    public Set<Quiz> getQuizzes(){
        return new LinkedHashSet<>(quizRepository.findAll());
    }
    public Quiz getQuizById(Long id){
        return quizRepository.findById(id)
                .orElseThrow(RuntimeException::new);
    }
    public void deleteQuiz(Long id){
        quizRepository.deleteById(id);
    }

    public List<Quiz> getQuizzesOfCategory(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(RuntimeException::new);
        return this.quizRepository.findByCategory(category);
    }
    public List<Quiz> getActiveQuizzes(){
        return quizRepository.findByActive(true);
    }
    public List<Quiz> getActiveQuizzesOfCategory(Long id){
        Category category = categoryRepository.findById(id)
                .orElseThrow(RuntimeException::new);
        return quizRepository.findByCategoryAndActive(category, true);
    }
}
