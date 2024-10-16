import { Component, inject, Input } from '@angular/core';
import { DemodataService } from './demodata.service';
import { Student } from './student';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'dependency';
  parentProperty: string = "";
  childProperty: string = "Hidden Treasure";

  private demoDataService: DemodataService = inject(DemodataService);
  demoObject: Student | undefined = this.demoDataService.getDemoData();

  getChildProperty(val: string): void {
    this.parentProperty = val;
  }


}
