import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { DistrictService } from 'src/app/services/district.service';

declare var bootstrap:any

@Component({
  templateUrl: './district-details.component.html',
  styles: [
  ]
})
export class DistrictDetailsComponent implements OnInit{

  targetDistrict: any

  districtEditDialog:any

  editData:any

  townships:any[] = []

  constructor(
    private route:ActivatedRoute,
    private districtApi:DistrictService) {
  }

  ngOnInit(): void {

    this.districtEditDialog = new bootstrap.Modal('#districtEditModal', {backdrop: false})

    this.route.queryParamMap.subscribe(params => {
      this.districtApi.findById(params.get('id')).subscribe(result => {
        this.targetDistrict = result
      })
    })
  }

  editDistrict(data:any) {
    if(data) {
      const {state, ... form} = data
      form.stateId = state.id
      this.editData = form
      // Show Edit Dialog
      this.districtEditDialog.show()
    }
  }

  saveDistrict(form:any) {
    this.districtApi.save(form).subscribe(result => {
      this.targetDistrict = result
      this.districtEditDialog.hide()
    })
  }
}
