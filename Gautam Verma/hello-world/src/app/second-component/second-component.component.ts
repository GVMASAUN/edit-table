import { Component, OnChanges, OnDestroy, OnInit, SimpleChanges } from '@angular/core';

@Component({
  selector: 'app-second-component',
  standalone: true,
  imports: [],
  templateUrl: './second-component.component.html',
  styleUrl: './second-component.component.css'
})
export class SecondComponentComponent implements OnInit, OnChanges, OnDestroy {
  ngOnInit(): void {
    console.log("Hello from second-component");
  }
  ngOnChanges(changes: SimpleChanges): void {
    console.log("second-component changes");
  }
  ngOnDestroy(): void {
      console.log("destroying second-component");
      
  }
}
