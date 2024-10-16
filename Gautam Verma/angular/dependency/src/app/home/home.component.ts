import { Component, EventEmitter, Input, Output } from '@angular/core';
import { DemodataService } from '../demodata.service';
import { Student } from '../student';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {

  constructor(private demoDataService: DemodataService) { };
  @Input() childProp: string = "";
  @Output() parentProp = new EventEmitter<string>();
  val: string = "";

  demoObject: Student | undefined = this.demoDataService.getDemoData();

  sendToParent(): void {
    this.parentProp.emit(this.val);
  }
}
