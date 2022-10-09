import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {QuizService} from "../../../services/quiz.service";

@Component({
  selector: 'app-load-quiz',
  templateUrl: './load-quiz.component.html',
  styleUrls: ['./load-quiz.component.css']
})
export class LoadQuizComponent implements OnInit {
  id: number =-1;
  quizzes: any;
  constructor(private router: ActivatedRoute, private quizService: QuizService) { }

  ngOnInit(): void {
    this.router.params.subscribe((params) => {
      this.id= params['id'];
      if(this.id==0){
        this.quizService.getActiveQuizzes().subscribe((data: any) => {
          this.quizzes=data;
          console.log(this.quizzes);
        }, (error) => {
          console.log(error);
        });
      }else {
        this.quizService.getActiveQuizzesOfCategory(this.id).subscribe((data: any)=> {
          this.quizzes=data;
          console.log(this.quizzes);
        }, (error) => {
          console.log(error);
        })
      }
    });
  }

}
