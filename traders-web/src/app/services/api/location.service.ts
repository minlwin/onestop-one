import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AbstractService } from './abstract.service';

@Injectable({
  providedIn: 'root'
})
export class LocationService extends AbstractService {

  constructor(private http:HttpClient) {
    super('location')
  }

  findDivisions() {
    return this.http.get<any[]>(`${this.baseApi}/division`)
  }

  findTownships(divisionId: string) {
    return this.http.get<any[]>(
      `${this.baseApi}/township`,
      {params: {division: divisionId}}
    )
  }
}
