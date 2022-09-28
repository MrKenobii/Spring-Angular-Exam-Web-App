import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {
  public user = {
    username: '',
    password: '',
    firstName: '',
    lastName: '',
    email: '',
    phone: ''
  }
  constructor() { }

  ngOnInit(): void {
  }

  formSubmit() {
    console.log(this.user);
    if(this.user.username==='' || this.user.username === null){
      alert("user is required");
      return;
    }
  }
}
