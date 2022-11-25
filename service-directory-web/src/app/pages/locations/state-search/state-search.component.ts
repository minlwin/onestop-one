import { Component, EventEmitter, Output } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { regionsConstant } from 'src/app/services';

@Component({
  selector: 'app-state-search',
  templateUrl: './state-search.component.html',
  styles: [
  ]
})
export class StateSearchComponent {

  form:FormGroup
  regions = regionsConstant

  @Output()
  onSearch = new EventEmitter<any>

  constructor(builder:FormBuilder) {
    this.form = builder.group({
      region: '',
      keyword: '',
      deleted: false
    })
  }

  search() {
    this.onSearch.emit(this.form.value)
  }

  addNew() {

  }

  upload() {

  }
}
