import { Component, EventEmitter, Output } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { regionsConstant } from 'src/app/services';

@Component({
  selector: 'app-service-search',
  templateUrl: './service-search-bar.component.html',
  styles: [
  ]
})
export class ServiceSearchBarComponent {

  form:FormGroup
  deletedLabel = 'Valid'
  regions = regionsConstant

  @Output("onSearch")
  emitter = new EventEmitter<any>

  constructor(builder:FormBuilder) {
    this.form = builder.group({
      type: '',
      region: '',
      state: '',
      keyword: '',
      deleted: false
    })

    this.form.get('deleted')?.valueChanges.subscribe(value => {
      this.deletedLabel = value ? 'Deleted' : 'Valid'
    })

    this.form.get('region')?.valueChanges.subscribe(value => {
      this.changeRegion(value)
    })
  }

  search() {
    this.emitter.emit(this.form.value)
  }

  changeRegion(region:any) {

  }
}
