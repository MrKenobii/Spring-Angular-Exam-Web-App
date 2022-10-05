import { Component, OnInit } from '@angular/core';
import {CategoryService} from "../../../services/category.service";
import Swal from "sweetalert2";

@Component({
  selector: 'app-view-categories',
  templateUrl: './view-categories.component.html',
  styleUrls: ['./view-categories.component.css']
})
export class ViewCategoriesComponent implements OnInit {
  categories=[
    {
      id: 23,
      title: 'programming',
      description: "test"
    },
    {
      id: 24,
      title: 'programming',
      description: "test"
    },
    {
      id: 25,
      title: 'programming',
      description: "test"
    },
    {
      id: 26,
      title: 'programming',
      description: "test"
    },
  ]
  constructor(private categoryService: CategoryService) { }

  ngOnInit(): void {
    this.categoryService.getCategories().subscribe((data:any) => {
        this.categories=data;
        console.log(this.categories);
    }, (error) => {
      console.log(error);
      Swal.fire("Error !!", "Error in loading data", 'error');
    })
  }

}
