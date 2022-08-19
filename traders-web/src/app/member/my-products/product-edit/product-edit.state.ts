import { SecurityContext } from './../../../services/security/sercurity-context';
import { FormBuilder, FormGroup, Validators, FormArray, FormControl } from '@angular/forms';
import { Injectable } from "@angular/core";

@Injectable()
export class ProductEditState {

  form:FormGroup
  view:string = ''
  readonly:boolean = true

  constructor(private builder:FormBuilder, context:SecurityContext) {
    this.form = builder.group({
      id: 0,
      sellerId: context.security?.id,
      name: ['', Validators.required],
      category: ['', Validators.required],
      condition: ['New', Validators.required],
      price: [0],
      photos: builder.array([]),
      features: builder.array([])
    })
  }

  get photos() {
    return this.form.get('photos') as FormArray<FormControl>
  }

  addPhoto(array: string[]) {
    array.forEach(p => this.photos.push(this.builder.control(p)))
  }

  deletePhoto(index:number) {
    this.photos.removeAt(index)
  }

  get features() {
    return this.form.get('features') as FormArray<FormGroup>
  }

  deleteFeature(index:number) {
    this.features.removeAt(index)
  }

  addFeature() {
    this.features.push(this.builder.group({
      name: '',
      value: ''
    }))
  }
}
