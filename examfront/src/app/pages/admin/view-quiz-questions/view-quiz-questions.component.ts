import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {QuestionService} from "../../../services/question.service";

@Component({
  selector: 'app-view-quiz-questions',
  templateUrl: './view-quiz-questions.component.html',
  styleUrls: ['./view-quiz-questions.component.css']
})
export class ViewQuizQuestionsComponent implements OnInit {
  id=-1;
  title='';
  questions= [
    {
      answer: "",
      content: "",
      id: -1,
      image: "",
      option1: "",
      option2: "",
      option3: "",
      option4: "",
    }
  ];
  constructor(private _router: ActivatedRoute, private questionService: QuestionService) { }

  ngOnInit(): void {
    this.id=this._router.snapshot.params['id'];
    this.title=this._router.snapshot.params['title'];
    this.questionService.getQuestionsOfQuiz(this.id).subscribe((data:any) => {
      console.log(data);
      this.questions=data;
    }, (error) => {
      console.log(error);
    });
    console.log("ID: " + this.id + ' Title: ' + this.title);
  }

}
