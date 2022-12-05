import { AfterContentChecked, AfterContentInit, AfterViewChecked, AfterViewInit, Component, DoCheck, Input, OnChanges, OnInit, SimpleChanges } from "@angular/core";

@Component({
  selector: 'app-hello',
  template: `
    <h5>Hello Angular Component</h5>

    <div>{{count}}</div>
  `,
  styles: [
    'h5 {font-size : 3rem}'
  ],
})
export class HelloComponent implements OnChanges, OnInit, DoCheck,
 AfterContentInit, AfterContentChecked, AfterViewInit, AfterViewChecked {

  @Input()
  count:number = 0

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
    console.log(`Child : ${message}`)
  }

}
