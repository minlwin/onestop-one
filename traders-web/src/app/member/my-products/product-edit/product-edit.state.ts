import { SecurityContext } from './../../../services/security/sercurity-context';
import { FormBuilder, FormGroup, Validators, FormArray, FormControl } from '@angular/forms';
import { Injectable } from "@angular/core";

@Injectable()
export class ProductEditState {

  form:FormGroup
  view:string = ''
  readonly:boolean = false

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

  init(value:any) {
    this.readonly = true
    const {features, photos, ... others} = value
    this.form.patchValue(others)

    this.photos.clear()
    this.addPhoto(photos)

    this.features.clear()
    for(let key in features) {
      this.features.push(this.builder.group({
        name: [key, Validators.required],
        value: [features[key], Validators.required]
      }))
    }
  }

  get formValue() {
    let {features, ... value} = this.form.value
    value['features'] = {}

    let array = features as any[]
    array.forEach(item => {
      value.features[item.name] = item.value
    })

    return value
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
      name: ['', Validators.required],
      value: ['', Validators.required]
    }))
  }
}
