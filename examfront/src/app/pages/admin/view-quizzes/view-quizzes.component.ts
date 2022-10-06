import { Component, OnInit } from '@angular/core';
import {QuizService} from "../../../services/quiz.service";
import Swal from "sweetalert2";


@Component({
  selector: 'app-view-quizzes',
  templateUrl: './view-quizzes.component.html',
  styleUrls: ['./view-quizzes.component.css']
})
export class ViewQuizzesComponent implements OnInit {
  quizzes = [
    {
      id:23,
      title: "Title",
      description: "Test",
      maxMarks: '50',
      numberOfQuestions: '20',
      active: '',
      category: {
        title: 'Java'
      }
    },
    {
      id:24,
      title: "Title",
      description: "Test",
      maxMarks: '50',
      numberOfQuestions: '20',
      active: '',
      category: {
        title: 'Java'
      }
    },
  ]
  constructor(private quizService: QuizService) { }

  ngOnInit(): void {
    this.quizService.getQuizzes().subscribe((data: any) => {
      this.quizzes=data;
    }, (error) => {
      console.log(error);
      Swal.fire('Error !', 'Error in loading quizzes', 'error');
    })

  }

}
