import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { DistrictService } from 'src/app/services/district.service';

@Component({
  templateUrl: './district-details.component.html',
  styles: [
  ]
})
export class DistrictDetailsComponent implements OnInit{

  targetDistrict: any

  constructor(
    private route:ActivatedRoute,
    private districtApi:DistrictService) {
  }

  ngOnInit(): void {
    this.route.queryParamMap.subscribe(params => {
      this.districtApi.findById(params.get('id')).subscribe(result => {
        this.targetDistrict = result
      })
    })
  }
}
