import { SecurityContext } from 'src/app/services/security/sercurity-context';
import { ActivatedRoute, Router } from '@angular/router';
import { LeftSideBar } from 'src/app/member/member-layout';
import { Component } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { SaleService, Status } from 'src/app/services/api/sale.service';

@Component({
  templateUrl: './sale-list.component.html',
})
export class SaleListComponent extends LeftSideBar {

  type:'sale' | 'purchase' = 'sale'
  list:any[] = []
  form:FormGroup

  constructor(
    route:ActivatedRoute,
    context:SecurityContext,
    builder:FormBuilder,
    private service:SaleService,
    private router:Router
  ) {
    super()
    this.form = builder.group({
      seller: '',
      buyer: '',
      status: '',
      keyword: ''
    })

    route.params.subscribe(params => {

      this.form.patchValue({
        seller: '',
        buyer: '',
        status: '',
        keyword: ''
      })

      if(params['type']) {
        this.type = params['type']
      }

      this.form.patchValue(this.type == 'sale' ?
        {seller : context.security?.id} :
        {buyer : context.security?.id})

      this.search()
    })

  }

  get statuses() {
    return Object.values(Status).filter(a => typeof a != 'number')
  }

  search() {
    this.service.search(this.form.value).subscribe(result => this.list = result)
  }

  showDetails(id:number) {
    this.router.navigate(['/member', 'sale', 'details'], {queryParams: {id: id}})
  }

}
