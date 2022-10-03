package com.mrkenobii.examserver.service;

import com.mrkenobii.examserver.model.exam.Quiz;
import com.mrkenobii.examserver.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.Set;

@Service
public class QuizService {
    private final QuizRepository quizRepository;
    @Autowired
    public QuizService(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }
    public Quiz addQuiz(Quiz quiz){
        return quizRepository.save(quiz);
    }
    public Quiz updateQuiz(Quiz quiz){
        return quizRepository.save(quiz);
    }
    public Set<Quiz> getQuizzes(){
        return new LinkedHashSet<>(quizRepository.findAll());
    }
    public Quiz getQuizById(Long id){
        return quizRepository.findById(id)
                .orElseThrow(RuntimeException::new);
    }
    public void deleteQuiz(Long id){
        Quiz quiz = new Quiz();
        quiz.setId(id);
        quizRepository.delete(quiz);
    }

}