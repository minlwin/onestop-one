import { Component, OnInit } from "@angular/core";
import { Router } from "@angular/router";
import { BalanceAppContext } from "../../services/balance-app.context";

@Component({
  template: ''
})
export class DefaultPageComponent implements OnInit{

  constructor(private router:Router, private context:BalanceAppContext) {}

  ngOnInit(): void {
    this.router.navigate(["/", this.context.loginUser?.role.toLocaleLowerCase() || 'signin'])
  }
}
