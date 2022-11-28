import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { DistrictService } from 'src/app/services/district.service';
import { StateService } from 'src/app/services/state.service';

declare var bootstrap:any

@Component({
  templateUrl: './state-details.component.html',
  styles: [
  ]
})
export class StateDetailsComponent implements OnInit{

  targetState:any
  targetDistrict:any

  editModal:any
  districtModal:any

  districts:any[] = []

  constructor(
    route:ActivatedRoute,
    private stateApi:StateService,
    private districtApi:DistrictService,
    private router:Router) {
    route.queryParamMap.subscribe(params => {
      const id = params.get('id')
      stateApi.findById(id).subscribe(result => this.targetState = result)
      districtApi.search({state: id}).subscribe(result => {
        this.districts = result
      })
    })
  }

  ngOnInit(): void {
    this.editModal = new bootstrap.Modal('#stateEditModal', {backdrop: false})
    this.districtModal = new bootstrap.Modal('#districtEditModal', {backdrop: false})
  }

  edit(event:boolean) {
    if(event) {
      this.editModal.show()
    }
  }

  save(form:any) {
    this.stateApi.save(form).subscribe(result => {
      this.targetState = result
      this.editModal.hide()
    })
  }

  addNewDistrict() {
    this.targetDistrict = {stateId: this.targetState?.id}
    this.districtModal.show()
  }

  saveDistrict(form:any) {
    this.districtApi.save(form).subscribe(result => {
      // Navigate to district details
      this.router.navigate(['/location', 'district-details'], {queryParams: {id: result.id}})
    })
  }
}
