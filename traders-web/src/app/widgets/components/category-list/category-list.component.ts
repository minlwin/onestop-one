import { Component, OnInit } from '@angular/core';
import { CategoryService } from '../../../services/api/category.service';

@Component({
  selector: 'app-category-list',
  templateUrl: './category-list.component.html',
  styles: [
  ]
})
export class CategoryListComponent implements OnInit {

  list:any[] = []

  constructor(private service:CategoryService) { }

  ngOnInit(): void {
    this.service.getCategories(5)
      .subscribe(result => this.list = result)
  }

  loadMore() {
    this.service.getCategories().subscribe(result => this.list = result)
  }

}
