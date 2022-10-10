import { Component, OnInit } from '@angular/core';
import {LocationStrategy} from "@angular/common";
import {ActivatedRoute} from "@angular/router";
import {QuestionService} from "../../../services/question.service";
import Swal from "sweetalert2";

@Component({
  selector: 'app-start',
  templateUrl: './start.component.html',
  styleUrls: ['./start.component.css']
})
export class StartComponent implements OnInit {
  id:number = -1;
  questions: any;
  marksGot: number = 0;
  correctAnswers: number = 0;
  attempted:number = 0;
  isSubmit: boolean = false;
  timer: any;
  constructor(private locationStrategy: LocationStrategy,
              private activatedRoute: ActivatedRoute,
              private questionService: QuestionService) { }

  ngOnInit(): void {
    this.preventBackButton();
    this.id=this.activatedRoute.snapshot.params['id'];
    this.loadQuestions();
  }
  preventBackButton(){
    history.pushState(null, 'null', location.href);
    this.locationStrategy.onPopState(() => {
        history.pushState(null, '', location.href);
    });
  }

  private loadQuestions() {
    this.questionService.getQuestionsOfQuizForTest(this.id).subscribe((data: any) => {
      this.questions=data;
      this.timer=this.questions.length*2*60;
      this.questions.forEach((q: any) => {
        q['givenAnswer']= '';
      });
      this.startTimer();
    }, (error) => {
      console.log(error);
      Swal.fire("Error", "<p> Error in loading questions </p>", "error")
    });
  }
  public submitQuiz(){
    Swal.fire({
      title: 'Do you want to submit quiz?',
      showCancelButton: true,
      confirmButtonText: 'Submit',
      icon: "info"
    }).then((result) => {
      if(result.isConfirmed){
        this.evalQuiz();
      }
    });
  }
  public startTimer(){
    let interval:any = window.setInterval(() => {
      if(this.timer <= 0){
        this.evalQuiz();
        clearInterval(interval);
      } else
        this.timer --;

    }, 1000);
  }
  public getFormattedTime(): string{
    console.log(this.timer);
    let mm= Math.floor(this.timer / 60);
    let ss=this.timer-mm*60;
    return `${mm} min :${ss}: sec `;
  }

  private evalQuiz(){
    this.isSubmit=true;
    this.questions.forEach((q: any) => {
      if(q.givenAnswer===q.answer) {
        this.correctAnswers++;
        let marksSingle = (this.questions[0].quiz.maxMarks) / (this.questions.length);
        this.marksGot += marksSingle;
      }
      if(q.givenAnswer.trim()!== ''){
        this.attempted++;
      }
    });
  }
}
