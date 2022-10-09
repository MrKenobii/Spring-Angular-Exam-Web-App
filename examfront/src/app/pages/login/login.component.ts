import { Component, OnInit } from '@angular/core';
import {MatSnackBar} from "@angular/material/snack-bar";
import {LoginService} from "../../services/login.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  loginData = {
    username: '',
    password: ''
  };
  constructor(private snack: MatSnackBar, private loginService: LoginService,private router: Router) { }

  ngOnInit(): void {
  }

  formSubmit() {
    if(this.loginData.username.trim()==='' || this.loginData.username === null){
      this.snack.open("Username is required !!", '', {
        duration: 3000
      });
      return;
    }
    if(this.loginData.password.trim()==='' || this.loginData.password === null){
      this.snack.open("Password is required !!", '', {
        duration: 3000
      });
      return;
    }
    this.loginService.generateToken(this.loginData).subscribe((data:any) => {
      console.log("Success");
      console.log(data);
      this.loginService.loginUser(data.token);
      this.loginService.getCurrentUser().subscribe((user: any) => {
        this.loginService.setUser(user);
        console.log(user);
        if(this.loginService.getUserRole()==="ADMIN"){
            this.router.navigate(['admin']).then(r => console.log(r));
            this.loginService.loginStatusSubject.next(true);
        } else if(this.loginService.getUserRole()==="NORMAL"){
            this.router.navigate(['user-dashboard/0']).then(r => console.log(r));
          this.loginService.loginStatusSubject.next(true);
        } else {
          this.loginService.logout();
        }
      });
    }, (error: any) => {
      console.log("Error: " +error);
      this.snack.open("Invalid Details !! Try Again", 'OK', {
        duration: 3000,
      });
    })
  }
}
