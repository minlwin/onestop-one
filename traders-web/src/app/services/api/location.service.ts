import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AbstractService } from './abstract.service';

@Injectable({
  providedIn: 'root'
})
export class LocationService extends AbstractService {

  constructor(private http:HttpClient) {
    super()
  }

  findDivisions() {
    return this.http.get<any[]>(`${this.getBaseApi('location')}/division`)
  }
}
