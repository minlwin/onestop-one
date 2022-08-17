import { SecurityContext } from './../security/sercurity-context';
import { HttpClient } from '@angular/common/http';
import { AbstractService } from './abstract.service';
import { Injectable } from '@angular/core';

@Injectable({providedIn: 'root'})
export class PhotoService extends AbstractService {

  constructor(private http:HttpClient, private security:SecurityContext) {
    super('photo')
  }

  upload(image:File) {
    const form = new FormData()
    form.append('file', image, image.name)
    return this.http.post<any>(`${this.baseApi}/${this.security.security?.id}`, form)
  }
}
