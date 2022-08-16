import { SecurityContext } from './../security/sercurity-context';
import { HttpRequest } from '@angular/common/http';
import { HttpClient } from '@angular/common/http';
import { AbstractService } from './abstract.service';
import { Injectable } from '@angular/core';

@Injectable({providedIn: 'any'})
export class PhotoService extends AbstractService {

  constructor(private http:HttpClient, private security:SecurityContext) {
    super('photo')
  }

  upload(image:File) {
    const form = new FormData()
    form.append('file', image, image.name)
    const request = new HttpRequest('POST', `${this.baseApi}/${this.security.security?.id}`, form)

    return this.http.request(request)
  }
}
