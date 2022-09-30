import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import baseUrl from "./helper";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) { }
  public addUser(user: any){
    return this.http.post(`${baseUrl}/user`, user);
  }
  public loginUser(token: string) : boolean {
    localStorage.setItem("token", token);
    return true;
  }
  public isLoggedIn(): boolean {
    let token =localStorage.getItem("token");
    if(token === undefined || token === '' || token === null) {
      return false;
    }
    return true;
  }
  public logout(): boolean {
    localStorage.removeItem("token");
    localStorage.removeItem("user");
    return true;
  }
  public getToken(): string{
    // @ts-ignore
    return localStorage.getItem("token");
  }
  public setUser(user: any) {
    localStorage.setItem("user", JSON.stringify(user));
  }
  public getUser(){
    let user = localStorage.getItem("user");
    if(user !== null)
      return JSON.parse(user);
    this.logout();
    return null;
  }
  public getUserRole(){
    let user = this.getUser();
    console.log(user);
    return user.authorities[0].authority;
  }

}
