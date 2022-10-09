import { Component, OnInit } from '@angular/core';
import {CategoryService} from "../../../services/category.service";
import {MatSnackBar} from "@angular/material/snack-bar";

@Component({
  selector: 'app-user-sidebar',
  templateUrl: './user-sidebar.component.html',
  styleUrls: ['./user-sidebar.component.css']
})
export class UserSidebarComponent implements OnInit {
  categories: any;
  constructor(private categoryService: CategoryService, private snackBar: MatSnackBar) { }

  ngOnInit(): void {
    this.categoryService.getCategories().subscribe((data: any) => {
    this.categories=data;
    }, (error) => {
      this.snackBar.open("Error in loading categories", "", {
        duration: 3000
      })
    });
  }

}
