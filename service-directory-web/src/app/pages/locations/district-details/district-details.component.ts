import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { DistrictService } from 'src/app/services/district.service';
import { TownshipService } from 'src/app/services/township.service';

declare var bootstrap:any

@Component({
  templateUrl: './district-details.component.html',
  styles: [
  ]
})
export class DistrictDetailsComponent implements OnInit{

  editData:any
  targetDistrict: any
  targetTownship:any

  townships:any[] = []

  districtEditDialog:any
  townshipEditDialog:any

  constructor(
    private route:ActivatedRoute,
    private districtApi:DistrictService,
    private townshipApi:TownshipService) {
  }

  ngOnInit(): void {

    this.districtEditDialog = new bootstrap.Modal('#districtEditModal', {backdrop: false})
    this.townshipEditDialog = new bootstrap.Modal('#townshipEditModal', {backdrop: false})

    this.route.queryParamMap.subscribe(params => {
      this.districtApi.findById(params.get('id')).subscribe(result => {
        this.targetDistrict = result
        this.loadTownship()
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

  editTownship(township:any) {
    if(township) {
      const {district, ... form} = township
      form.districtId = district.id
      this.targetTownship = form
    } else {
      this.targetTownship = {districtId: this.targetDistrict.id}
    }

    this.townshipEditDialog.show()
  }

  saveTownship(township:any) {
    this.townshipApi.save(township).subscribe(() => {
      this.loadTownship()
      this.townshipEditDialog.hide()
    })
  }

  private loadTownship() {
    this.townshipApi.search({district: this.targetDistrict.id})
      .subscribe(result => this.townships =result)
  }
}
