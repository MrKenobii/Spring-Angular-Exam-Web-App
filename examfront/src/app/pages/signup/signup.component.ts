import { Component, OnInit } from '@angular/core';
import {UserService} from "../../services/user.service";
import {MatSnackBar} from "@angular/material/snack-bar";
import Swal from 'sweetalert2';

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
  constructor(private userService: UserService, private snackBar:MatSnackBar) { }

  ngOnInit(): void {
  }

  formSubmit() {
    console.log(this.user);
    if(this.user.username==='' || this.user.username === null){
      this.snackBar.open("Username is required !!", 'OK', {
        duration: 3000,
        verticalPosition: 'top',
        horizontalPosition: 'right'
      });
      return;
    }
    this.userService.addUser(this.user).subscribe((data:any) => {
      console.log(data);
      Swal.fire('Success', 'user registration completed successfully', 'success');
    }, (error) => {
      console.log(error);
      this.snackBar.open("Something went wrong", 'OK', {
        duration: 3000
      });
    });
  }
}
