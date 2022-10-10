import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {QuizService} from "../../../services/quiz.service";
import Swal from "sweetalert2";

@Component({
  selector: 'app-instructions',
  templateUrl: './instructions.component.html',
  styleUrls: ['./instructions.component.css']
})
export class InstructionsComponent implements OnInit {
  id: number = -1;
  quiz: any;
  constructor(private router: ActivatedRoute, private quizService: QuizService, private _router: Router) { }

  ngOnInit(): void {
    this.id= this.router.snapshot.params["id"];
    this.quizService.getQuizById(this.id).subscribe((data: any) => {
      this.quiz=data;
    }, (error) => {
      console.log(error);
    });
  }

  startQuiz() {
    Swal.fire({
      title: 'Do you want to start quiz?',
      showCancelButton: true,
      confirmButtonText: 'Start',
      denyButtonText: "Don't Start",
      icon: "info"
    }).then((result) => {
      if(result.isConfirmed)
        this._router.navigate(['/start/' + this.id]);
      else if(result.isDenied)
        Swal.fire("Not Started!", "", "info");
    });
  }
}
