import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { handleApiResult } from 'src/app/shared/services';
import { MemberService } from 'src/app/shared/services/member.service';

@Component({
  templateUrl: './members.component.html',
  styles: [
  ]
})
export class MembersComponent implements OnInit{

  form:FormGroup
  list:any[] = []

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
      this.list = handleApiResult(result)
    })
  }
}
