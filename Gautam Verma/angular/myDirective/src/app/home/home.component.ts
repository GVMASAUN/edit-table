import { Component } from '@angular/core';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {
  toggle: boolean = false;
  switchValue: string;
  template = { name: "Gautam Verma" }

  color: string = 'red';
  condition: boolean = true;

  users = [
    { id: 1, name: "Gautam" },
    { id: 2, name: "Sahil" },
    { id: 3, name: "Sarbjit" },
    { id: 4, name: "Nitin" },
  ]

  toggleColor(): void {
    this.toggle = !this.toggle;
    if (this.toggle) {
      this.color = 'violet';
    } else {
      this.color = 'pink';
    }
  }
}
