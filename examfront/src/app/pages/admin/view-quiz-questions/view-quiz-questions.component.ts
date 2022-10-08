import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {QuestionService} from "../../../services/question.service";
import Swal from "sweetalert2";
import {MatSnackBar} from "@angular/material/snack-bar";

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
  constructor(private _router: ActivatedRoute, private questionService: QuestionService, private _snackBar: MatSnackBar) { }

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

  deleteQuestion(id: number) {
    Swal.fire({
      icon:"info",
      showCancelButton:true,
      confirmButtonAriaLabel: 'Delete',
      title: 'Are you sure?'
    }).then((result) => {
      if(result.isConfirmed)
        this.questionService.deleteQuestion(id).subscribe((data: any) => {
          this._snackBar.open('Question deleted', '', {
            duration: 3000
          });
          this.questions=this.questions.filter((q) => q.id !== id);
        });
    }, (error) => {
      this._snackBar.open("Error on deleting question with id: " + id, '', {
        duration: 3000
      });
      console.log(error);
    });
  }
}
