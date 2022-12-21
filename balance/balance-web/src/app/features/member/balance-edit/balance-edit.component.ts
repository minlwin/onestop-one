import { Component } from '@angular/core';
import { FormArray, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { handleApiResult } from 'src/app/shared/services';
import { BalanceService } from 'src/app/shared/services/balance.service';
import { CategoryService } from 'src/app/shared/services/category.service';

@Component({
  templateUrl: './balance-edit.component.html',
  styles: [
  ]
})
export class BalanceEditComponent {

  form:FormGroup
  categories:any[] = []

  constructor(
    route:ActivatedRoute,
    categoryService:CategoryService,
    private builder:FormBuilder,
    private balanceService:BalanceService,
    private router:Router
    ) {
    this.form = builder.group({
      id: 0,
      type: '',
      categoryId: ['', Validators.min(1)],
      issueAt: ['', Validators.required],
      remark: ['', Validators.required],
      details: builder.array([])
    })

    this.addDetailsItem(undefined)

    this.form.get('type')?.valueChanges.subscribe(type => {
      categoryService.search({type: type}).subscribe(result => {
        this.categories = handleApiResult(result)
        this.form.patchValue({categoryId: ''})
      })
    })

    route.paramMap.subscribe(params => {
      if(params.get('type')) {
        this.form.patchValue({type: params.get('type')})
      }
    })

    route.queryParamMap.subscribe(params => {
      if(params.get('id')) {
        balanceService.findById(+params.get('id')!).subscribe(result => {
          const {category, details, deleted, fixed, ... dto} = handleApiResult(result)
          this.form.patchValue(dto)
          this.form.patchValue({categoryId: category.id})
          this.detailsList.clear()
          this.patchDetails(details)
        })
      }
    })
  }

  get detailsList(): FormArray {
    return this.form.get('details') as FormArray
  }

  patchDetails(list:any[]) {
    for(const item of list) {
      this.addDetailsItem(item)
    }
  }

  addDetailsItem(item:any) {
    this.detailsList.push(this.builder.group({
      id: item?.id || 0,
      article: [item?.article || '', Validators.required],
      unitPrice: [item?.unitPrice || 0, Validators.min(1)],
      quentity: [item?.quentity || 0, Validators.min(1)],
      deleted: false,
    }))
  }

  removeDetailsItem(index:number) {
    const item = this.detailsList.at(index).value
    if(item.id) {
      item.patchValue({deleted: true})
    } else {
      this.detailsList.removeAt(index)

      if(this.detailsList.controls.length == 0) {
        this.addDetailsItem(undefined)
      }
    }
  }

  detailsTotal(index:number):number {
    const detailsForm = this.detailsList.controls.at(index) as FormGroup
    return (detailsForm.get('unitPrice')?.value || 0) * (detailsForm.get('quentity')?.value || 0)
  }

  get allTotal() {
    return this.detailsList.controls
      .map(detailsForm => (detailsForm.get('unitPrice')?.value || 0) * (detailsForm.get('quentity')?.value || 0))
      .reduce((a, b) => a + b)
  }

  save() {
    if(this.form.valid) {
      this.balanceService.save(this.form.value).subscribe(result => {
        const dto = handleApiResult(result)
        this.router.navigate(['/member', 'details', dto.id])
      })
    }
  }
}
