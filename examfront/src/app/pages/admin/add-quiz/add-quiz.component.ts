import { Component, OnInit } from '@angular/core';
import {CategoryService} from "../../../services/category.service";
import Swal from "sweetalert2";
import {MatSnackBar} from "@angular/material/snack-bar";
import {QuizService} from "../../../services/quiz.service";

@Component({
  selector: 'app-add-quiz',
  templateUrl: './add-quiz.component.html',
  styleUrls: ['./add-quiz.component.css']
})
export class AddQuizComponent implements OnInit {
  categories =[
    {
      id: -1,
      title: "",
    }
  ];
  quizData = {
    title: '',
    description: '',
    maxMarks: '',
    numberOfQuestions: '',
    active:true,
    category: null
  }
  constructor(private categoryService: CategoryService, private quizService: QuizService, private snackBar: MatSnackBar) { }

  ngOnInit(): void {
    this.categoryService.getCategories().subscribe((data: any) => {
        this.categories=data;
    }, (error) => {
      console.log(error);
      Swal.fire("Error !!", "Error loading categories", "error");
    });
  }

  addQuiz() {
    if(this.quizData.title.trim()==='' || this.quizData.title===null){
      this.snackBar.open("Title Required !!", '', {
        duration: 3000
      });
      return;
    }
    this.quizService.addQuiz(this.quizData).subscribe((data: any) => {
      Swal.fire("Success", "Quiz is added", "success");
      this.quizData = {
        title: '',
        description: '',
        maxMarks: '',
        numberOfQuestions: '',
        active:true,
        category: null
      }
    }, (error) => {
      console.log(error);
      Swal.fire("Error !!", "Error adding quiz", "error");
    });
  }
}
