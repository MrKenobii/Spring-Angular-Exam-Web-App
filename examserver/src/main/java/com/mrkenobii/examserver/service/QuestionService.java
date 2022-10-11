package com.mrkenobii.examserver.service;

import com.mrkenobii.examserver.model.exam.Question;
import com.mrkenobii.examserver.model.exam.Quiz;
import com.mrkenobii.examserver.repository.QuestionRepository;
import com.mrkenobii.examserver.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class QuestionService {
    private final QuestionRepository questionRepository;
    private final QuizRepository quizRepository;
    private final QuizService quizService;
    @Autowired
    public QuestionService(QuestionRepository questionRepository, QuizRepository quizRepository, QuizService quizService) {
        this.questionRepository = questionRepository;
        this.quizRepository = quizRepository;
        this.quizService = quizService;
    }
    public Question addQuestion(Question question){
        return questionRepository.save(question);
    }
    public Question updateQuestion(Question question){
//        Question question1 = new Question();

        Question question1 = questionRepository.findById(question.getId())
                .orElseThrow(RuntimeException::new);
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
    public Set<Question> getQuestionsOfAdmin(Long id){
        Quiz quiz = quizRepository.findById(id)
                .orElseThrow(RuntimeException::new);
        return questionRepository.findByQuiz(quiz);
    }
    public void deleteQuestionById(Long id){
        questionRepository.deleteById(id);
    }
    public Question get(Long id){
        return questionRepository.getOne(id);
    }
    public Map<String, Object> evalQuiz(List<Question> questions){
        double marksGot = 0;
        int correctAnswers = 0;
        int attempted = 0;
        for(Question q: questions) {
            Question question = this.get(q.getId());
            if(question.getAnswer().equals(q.getGivenAnswer())){
                correctAnswers++;
                double marksSingle = Double.parseDouble(questions.get(0).getQuiz().getMaxMarks())/ questions.size();
                marksGot += marksSingle;
            }
            if(!q.getGivenAnswer().trim().equals("")){
                attempted++;
            }
        }
        Map<String, Object> of = Map.of("marksGot", marksGot, "correctAnswers", correctAnswers, "attempted", attempted);
        return of;
    }

    public List<Question> getQuestionsOfQuiz(Long id) {
        Quiz quizById = quizService.getQuizById(id);
        Set<Question> questions = quizById.getQuestions();

        List<Question> list = new ArrayList<>(questions);
        if(list.size() > Integer.parseInt(quizById.getNumberOfQuestions()))
            list = list.subList(0, Integer.parseInt(quizById.getNumberOfQuestions()));
        Collections.shuffle(list);
        list.forEach(q -> {
            q.setAnswer("");
        });
        return list;
    }
}
