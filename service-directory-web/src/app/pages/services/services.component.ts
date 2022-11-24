import { Component } from '@angular/core';

@Component({
  templateUrl: './services.component.html',
})
export class ServicesComponent {


  search(form:any) {
    console.log(form)
  }
}
