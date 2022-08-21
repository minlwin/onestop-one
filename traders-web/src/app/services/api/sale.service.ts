import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AbstractService } from './abstract.service';

@Injectable({providedIn: 'root'})
export  class SaleService extends AbstractService {

  constructor(private http:HttpClient) {
    super('sale')
  }
}
