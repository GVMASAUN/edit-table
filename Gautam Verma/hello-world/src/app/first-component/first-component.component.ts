import { AfterViewInit, Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-first-component',
  standalone: true,
  imports: [],
  templateUrl: './first-component.component.html',
  styleUrl: './first-component.component.css'
})
export class FirstComponentComponent implements OnInit, AfterViewInit{
  ngOnInit(): void {
      console.log("Starting of first-component")
  }
  ngAfterViewInit(): void {
      console.log("Displaying after view initiliastaion")
  }
}
