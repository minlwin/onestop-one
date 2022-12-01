import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { regionsConstant } from 'src/app/services';

@Component({
  selector: 'app-state-search',
  templateUrl: './state-search.component.html',
  styles: [
  ]
})
export class StateSearchComponent implements OnInit{

  form:FormGroup
  regions = regionsConstant

  @Output()
  onSearch = new EventEmitter<any>

  @Output()
  onAddNew = new EventEmitter<boolean>

  @Output()
  onUpload = new EventEmitter<any>

  constructor(builder:FormBuilder) {
    this.form = builder.group({
      region: '',
      keyword: '',
      deleted: false
    })
  }

  ngOnInit(): void {
    this.search()
  }

  search() {
    this.onSearch.emit(this.form.value)
  }

  addNew() {
    this.onAddNew.emit(true)
  }

  upload(files:FileList) {
    if(files.length > 0) {
      this.onUpload.emit(files[0])
    }
  }
}
