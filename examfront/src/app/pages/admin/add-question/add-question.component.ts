import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-add-question',
  templateUrl: './add-question.component.html',
  styleUrls: ['./add-question.component.css']
})
export class AddQuestionComponent implements OnInit {
  id=-1;
  title='';
  question= {
    quiz:{
      id:-1,
      title: '',
      description: '',
      maxMarks: '',
      numberOfQuestions: '',
      active:true,
      category: null
    },
    content:'',
    option1:'',
    option2:'',
    option3:'',
    option4:'',
    answer:'',
  }
  constructor(private _router: ActivatedRoute) { }

  ngOnInit(): void {
    this.id=this._router.snapshot.params['id'];
    this.title=this._router.snapshot.params['title'];
    this.question.quiz['id'] = this.id;
  }

}
