package com.mrkenobii.examserver.controller;

import com.mrkenobii.examserver.model.exam.Question;
import com.mrkenobii.examserver.model.exam.Quiz;
import com.mrkenobii.examserver.service.QuestionService;
import com.mrkenobii.examserver.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

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
        Quiz quizById = quizService.getQuizById(id);
        Set<Question> questions = quizById.getQuestions();

        List list = new ArrayList(questions);
        if(list.size() > Integer.parseInt(quizById.getNumberOfQuestions()))
            list = list.subList(0, Integer.parseInt(quizById.getNumberOfQuestions()));
        Collections.shuffle(list);
        return ResponseEntity.ok(list);
    }
    @GetMapping("/quiz/all/{id}")
    public ResponseEntity<?> getQuestionsOfQuizAdmin(@PathVariable Long id){
        Quiz quiz = new Quiz();
        quiz.setId(id);
        return  ResponseEntity.ok(questionService.getQuestionsOfQuiz(quiz));
        //return ResponseEntity.ok(list);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Question> getQuestionById(@PathVariable Long id){
        return new ResponseEntity<>(questionService.getQuestionById(id), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public void deleteQuestionById(@PathVariable Long id){
        questionService.deleteQuestionById(id);
    }

}
