package com.mrkenobii.examserver.controller;

import com.mrkenobii.examserver.model.exam.Category;
import com.mrkenobii.examserver.model.exam.Quiz;
import com.mrkenobii.examserver.service.QuizService;
import org.hibernate.annotations.Fetch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/quiz")
@CrossOrigin("*")
public class QuizController {
    private final QuizService quizService;
    @Autowired
    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }
    @PostMapping
    public ResponseEntity<Quiz> addQuiz(@RequestBody Quiz quiz){
        return new ResponseEntity<>(quizService.addQuiz(quiz), HttpStatus.CREATED);
    }
    @PutMapping
    public ResponseEntity<Quiz> updateQuiz(@RequestBody Quiz quiz){
        return new ResponseEntity<>(quizService.updateQuiz(quiz), HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<Set<Quiz>> getQuizzes(){
        return new ResponseEntity<>(quizService.getQuizzes(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Quiz> getQuizById(@PathVariable Long id){
        return new ResponseEntity<>(quizService.getQuizById(id), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public void deleteQuiz(@PathVariable Long id){
        quizService.deleteQuiz(id);
    }
    @GetMapping("/category/{id}")
    public ResponseEntity<List<Quiz>> getQuizzesOfCategory(@PathVariable Long id){
        return new ResponseEntity<>(quizService.getQuizzesOfCategory(id), HttpStatus.OK);
    }
    @GetMapping("/active")
    public ResponseEntity<List<Quiz>> getActiveQuizzes(){
        return new ResponseEntity<>(quizService.getActiveQuizzes(), HttpStatus.OK);
    }
    @GetMapping("/category/active/{id}")
    public ResponseEntity<List<Quiz>> getActiveQuizzesOfCategory(@PathVariable Long id){
        return new ResponseEntity<>(quizService.getActiveQuizzesOfCategory(id), HttpStatus.OK);
    }

}
