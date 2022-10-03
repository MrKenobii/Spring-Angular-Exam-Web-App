package com.mrkenobii.examserver.service;

import com.mrkenobii.examserver.model.exam.Question;
import com.mrkenobii.examserver.model.exam.Quiz;
import com.mrkenobii.examserver.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.Set;

@Service
public class QuestionService {
    private final QuestionRepository questionRepository;
    @Autowired
    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }
    public Question addQuestion(Question question){
        return questionRepository.save(question);
    }
    public Question updateQuestion(Question question){
        return questionRepository.save(question);
    }
    public Set<Question> getQuestions(){
        return new LinkedHashSet<>(questionRepository.findAll());
    }
    public Question getQuestionById(Long id){
        return questionRepository.findById(id)
                .orElseThrow(RuntimeException::new);
    }
    public Set<Question> getQuestionsOfQuiz(Quiz quiz){
        return questionRepository.findByQuiz(quiz);
    }
}
