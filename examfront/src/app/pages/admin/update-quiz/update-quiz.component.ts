import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {QuizService} from "../../../services/quiz.service";
import {CategoryService} from "../../../services/category.service";
import Swal from "sweetalert2";

@Component({
  selector: 'app-update-quiz',
  templateUrl: './update-quiz.component.html',
  styleUrls: ['./update-quiz.component.css']
})
export class UpdateQuizComponent implements OnInit {
  id=0;
  quiz={
    title: '',
    description: '',
    maxMarks: '',
    numberOfQuestions: '',
    active:true,
    category: null
  };
  categories =[
    {
      id: -1,
      title: "",
    }
  ];
  constructor(private router: ActivatedRoute, private quizService: QuizService, private categoryService: CategoryService, private _router: Router) { }

  ngOnInit(): void {
    this.id=this.router.snapshot.params['id'];
    this.quizService.getQuizById(this.id).subscribe((data: any) => {
      this.quiz=data;
      console.log(this.quiz);
    }, (error) => {
      console.log(error);
    });
    this.categoryService.getCategories().subscribe((data: any) => {
      this.categories=data;
    }, (error) => {
      console.log(error);
      Swal.fire("Error !!", "Error loading categories", "error");
    });
  }
  public updateData(){
    this.quizService.updateQuiz(this.quiz).subscribe((data: any) => {
      Swal.fire("Updated!!", "Quiz updated with id " + this.id, "success").then((e) => {
        this._router.navigate(['admin/quizzes']);
      });

    }, (error) => {
      Swal.fire("Error", "Error updating quiz", "error");
      console.log(error);
    });
  }

}
