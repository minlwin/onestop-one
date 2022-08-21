import { ActivatedRoute, Router } from '@angular/router';
import { ProductSerivce } from './../../../services/api/product.service';
import { ProductEditState } from './product-edit.state';
import { Component, OnInit } from '@angular/core';

@Component({
  templateUrl: './product-edit.component.html',
  providers: [
    ProductEditState
  ]
})
export class ProductEditComponent implements OnInit {

  constructor(
    public state:ProductEditState,
    private service:ProductSerivce,
    private route:ActivatedRoute,
    private router:Router
  ) { }

  ngOnInit(): void {
    this.route.queryParams.subscribe(param => {
      if(param['id']) {
        this.service.findById(param['id']).subscribe(result => this.state.init(result))
      }
    })
  }

  get showPhotoBtn() {
    return this.state.form.get('id')?.value > 0
  }

  get showAddFeaturesBtn() {
    return (!this.state.readonly) && this.state.view == 'features'
  }

  save() {
    if(this.state.form.valid) {
      this.service.save(this.state.formValue)
        .subscribe(result => {
          this.router.navigate(['/member', 'products', 'details', result.id])
        })
    }
  }

  uploadImage(files:FileList | null) {
    if(files) {
      this.service.uploadPhoto(this.state.form.get('id')?.value, files)
        .subscribe(result => {
          this.router.navigate(['/member', 'products', 'details', result.id])
        })
    }
  }
}
