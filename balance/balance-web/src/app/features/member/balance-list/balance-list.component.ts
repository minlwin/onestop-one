import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { handleApiResult } from 'src/app/shared/services';
import { BalanceService } from 'src/app/shared/services/balance.service';
import { CategoryService } from 'src/app/shared/services/category.service';

@Component({
  templateUrl: './balance-list.component.html',
  styles: [
  ]
})
export class BalanceListComponent implements OnInit{

  categories:any[] = []
  form:FormGroup
  list:any[] = []

  constructor(builder:FormBuilder,
    private route:ActivatedRoute,
    categoryService:CategoryService,
    private service:BalanceService) {

    this.form = builder.group({
      type: '',
      category: '',
      keyword: ''
    })

    this.form.get('type')?.valueChanges.subscribe(type => {
      this.categories = []
      this.form.patchValue({category: ''})
      categoryService.search(type).subscribe(
        result => this.categories = handleApiResult(result)
      )
    })
  }

  ngOnInit(): void {
    this.route.paramMap.subscribe(
      params => this.form.patchValue({type: params.get('type') || ''})
    )
  }

  search() {
    this.service.search(this.form.value).subscribe(
      result => this.list = handleApiResult(result)
    )
  }

}
