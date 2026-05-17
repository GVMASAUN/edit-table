import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { EditTableComponent } from "./edit-table/edit-table.component";
import { MainpulateTableComponent } from "./mainpulate-table/mainpulate-table.component";

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, EditTableComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
}
