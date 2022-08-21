import { AbstractService } from './abstract.service';
import { HttpClient } from '@angular/common/http';
import { Injectable } from "@angular/core";

@Injectable({providedIn: 'root'})
export class ConversationService extends AbstractService{

  constructor(private http:HttpClient) {
    super('conversation')
  }

  search(form:any) {
    return this.http.get<any[]>(this.baseApi, {params: form})
  }

  findById(id:number) {
    return this.http.get<any>(`${this.baseApi}/${id}`)
  }

  create(form:any) {
    return this.http.post(this.baseApi, form)
  }

  sendMessage(form:any) {
    return this.http.post(`${this.baseApi}/message`, form)
  }

}
