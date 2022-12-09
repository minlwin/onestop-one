import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Course } from "./model";

const API = 'http://localhost:8080/course'

@Injectable({providedIn: 'root'})
export class CourseApi {

  constructor(private http:HttpClient) {}

  search(form:any) {
    return this.http.get<Course[]>(API, {params: form})
  }

  findById(id:any) {
    return this.http.get<Course>(`${API}/${id}`)
  }

  save(dto:Course) {
    const {id, ... form} = dto
    return id == 0 ? this.http.post<Course>(API, form) :
      this.http.put(`${API}/${id}`, form)
  }
}
