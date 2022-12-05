import { AfterContentChecked, AfterContentInit, AfterViewChecked, AfterViewInit, Component, DoCheck, OnChanges, OnInit, SimpleChanges } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
})
export class AppComponent implements OnChanges, OnInit, DoCheck,
  AfterContentInit, AfterContentChecked,
  AfterViewInit, AfterViewChecked{

  title = 'the-components';

  count = 0

  countUp() {
    this.count ++
  }

  constructor() {
    this.log("Constructor Call")
  }

  ngOnChanges(changes: SimpleChanges): void {
    this.log("On Changes")
  }

  ngOnInit(): void {
    this.log("On Init")
  }

  ngDoCheck(): void {
    this.log("Do Check")
  }

  ngAfterContentInit(): void {
    this.log("After Content Init")
  }

  ngAfterContentChecked(): void {
    this.log("After Content Checked")
  }

  ngAfterViewInit(): void {
    this.log("After View Init")
  }

  ngAfterViewChecked(): void {
    this.log("After View Checked")
  }

  private log(message:string) {
    console.log(`Parent : ${message}`)
  }
}
