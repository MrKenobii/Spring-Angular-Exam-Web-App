import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {QuizService} from "../../../services/quiz.service";

@Component({
  selector: 'app-instructions',
  templateUrl: './instructions.component.html',
  styleUrls: ['./instructions.component.css']
})
export class InstructionsComponent implements OnInit {
  id: number = -1;
  quiz: any;
  constructor(private router: ActivatedRoute, private quizService: QuizService) { }

  ngOnInit(): void {
    this.id= this.router.snapshot.params["id"];
    this.quizService.getQuizById(this.id).subscribe((data: any) => {
      this.quiz=data;
    }, (error) => {
      console.log(error);
    });
  }

}
