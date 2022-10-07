import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import baseUrl from "./helper";

@Injectable({
  providedIn: 'root'
})
export class QuestionService {

  constructor(private http: HttpClient) { }
  public getQuestionsOfQuiz(id: number){
    return this.http.get(`${baseUrl}/question/quiz/all/${id}`);
  }
}
