import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import baseUrl from "./helper";

@Injectable({
  providedIn: 'root'
})
export class QuizService {

  constructor(private http: HttpClient) { }
  public getQuizzes(){
    return this.http.get(`${baseUrl}/quiz`);
  }
  public addQuiz(quiz: any){
    return this.http.post(`${baseUrl}/quiz`, quiz);
  }

  public deleteQuiz(id: number) {
    return this.http.delete(`${baseUrl}/quiz/${id}`);
  }
  public getQuizById(id: number){
    return this.http.get(`${baseUrl}/quiz/${id}`);
  }
  public updateQuiz(quiz: any){
      return this.http.put(`${baseUrl}/quiz`, quiz);
  }

}
