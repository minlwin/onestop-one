import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { MemberService } from 'src/app/shared/services/member.service';

@Component({
  templateUrl: './members.component.html',
  styles: [
  ]
})
export class MembersComponent implements OnInit{

  form:FormGroup
  list:any[] = []
  messages:string[] = []

  constructor(builder:FormBuilder, private service:MemberService) {
    this.form = builder.group({
      role: '',
      keyword: ''
    })
  }

  ngOnInit(): void {
    this.search()
  }

  search() {
    this.service.search(this.form.value).subscribe(result => {
      this.list = []
      this.messages = []
      if(result.success) {
        this.list = result.data
        if(this.list.length == 0) {
          this.messages.push('There is no data.')
        }
      } else {
        this.messages = result.data
      }
    })
  }
}
