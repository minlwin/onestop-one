import { Component, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { handleApiResult } from 'src/app/shared/services';
import { CategoryService } from 'src/app/shared/services/category.service';
import { CategoryEditComponent } from './category-edit/category-edit.component';

@Component({
  templateUrl: './categories.component.html',
  styles: [
  ]
})
export class CategoriesComponent implements OnInit{

  list:any[] = []
  form:FormGroup

  @ViewChild(CategoryEditComponent)
  modalDialog?:CategoryEditComponent

  constructor(builder:FormBuilder, private service:CategoryService) {
    this.form = builder.group({
      type: '',
      keyword: ''
    })
  }

  ngOnInit(): void {
    this.search()
  }

  search() {
    this.service.search(this.form.value).subscribe(result => this.list = handleApiResult(result))
  }

  upload(files:FileList) {
    if(files.length > 0) {
      this.service.upload(files![0]).subscribe(result => {
        this.list = handleApiResult(result)
      })
    }
  }

  edit(data:any) {
    this.modalDialog?.show(data)
  }

  save(data:any) {
    this.service.save(data).subscribe(() => this.search())
  }
}
