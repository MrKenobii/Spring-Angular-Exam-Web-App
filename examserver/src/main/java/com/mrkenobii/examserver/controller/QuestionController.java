package com.mrkenobii.examserver.controller;

import com.mrkenobii.examserver.model.exam.Question;
import com.mrkenobii.examserver.model.exam.Quiz;
import com.mrkenobii.examserver.service.QuestionService;
import com.mrkenobii.examserver.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/v1/question")
@CrossOrigin("*")
public class QuestionController {
    private final QuestionService questionService;
    private final QuizService quizService;
    @Autowired
    public QuestionController(QuestionService questionService, QuizService quizService) {
        this.questionService = questionService;
        this.quizService = quizService;
    }
    @PostMapping
    public ResponseEntity<Question> addQuestion(@RequestBody Question question){
        return new ResponseEntity<>(questionService.addQuestion(question), HttpStatus.CREATED);
    }
    @PutMapping
    public ResponseEntity<Question> updateQuestion(@RequestBody Question question){
        return new ResponseEntity<>(questionService.updateQuestion(question), HttpStatus.OK);
    }
    @GetMapping("/quiz/{id}")
    public ResponseEntity<?> getQuestionsOfQuiz(@PathVariable Long id){
        return new ResponseEntity<>(questionService.getQuestionsOfQuiz(id), HttpStatus.OK);
    }
    @GetMapping("/quiz/all/{id}")
    public ResponseEntity<?> getQuestionsOfQuizAdmin(@PathVariable Long id){
        return  ResponseEntity.ok(questionService.getQuestionsOfAdmin(id));
    }
    @GetMapping("/{id}")
    public ResponseEntity<Question> getQuestionById(@PathVariable Long id){
        return new ResponseEntity<>(questionService.getQuestionById(id), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public void deleteQuestionById(@PathVariable Long id){
        questionService.deleteQuestionById(id);
    }

    @PostMapping("/eval-quiz")
    public ResponseEntity<Map<String, Object>> evalQuiz(@RequestBody List<Question> questions){
        return new ResponseEntity<>(questionService.evalQuiz(questions), HttpStatus.OK);
    }
}
