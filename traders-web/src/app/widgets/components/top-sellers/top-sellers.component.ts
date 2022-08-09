import { Component, OnInit } from '@angular/core';
import { TopSellersService } from '../../../services/api/top-sellers.service';

@Component({
  selector: 'app-top-sellers',
  templateUrl: './top-sellers.component.html',
  styles: [
  ]
})
export class TopSellersComponent implements OnInit {

  list:any[] = []

  constructor(private service:TopSellersService) { }

  ngOnInit(): void {
    this.service.getTopSellers()
      .subscribe(result => this.list = result)
  }

}
