import { Component, OnDestroy, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ObservableChildComponent } from './observable-child/observable-child.component';
import { Subject, Subscription, tap } from 'rxjs';

@Component({
  standalone: true,
  imports: [CommonModule, ObservableChildComponent],
  templateUrl: './using-observables.component.html',
})
export class UsingObservablesComponent implements OnInit{

  list:string[] = []

  ngOnInit(): void {
    console.log("Init")
  }
}
