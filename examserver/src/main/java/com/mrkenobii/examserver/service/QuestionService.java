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
        Question question1 = new Question();
        question1.setAnswer(question.getAnswer());
        question1.setContent(question.getContent());
        question1.setImage(question.getImage());
        question1.setOption1(question.getOption1());
        question1.setOption2(question.getOption2());
        question1.setOption3(question.getOption3());
        question1.setOption4(question.getOption4());
        question1.setQuiz(question.getQuiz());

        return questionRepository.save(question1);
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
    public void deleteQuestionById(Long id){
        Question question = new Question();
        question.setId(id);
        questionRepository.delete(question);
    }
}
