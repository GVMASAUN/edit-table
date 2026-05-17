import { AfterViewChecked, AfterViewInit, Component, ElementRef, Input, OnChanges, OnInit, ViewChild } from '@angular/core';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements AfterViewChecked {
  newVar: string;

  @ViewChild('input') hey: ElementRef;

  ngAfterViewChecked(): void {
    // console.log("Hello Ishika");
    // console.log(this.hey.nativeElement.value);
    console.log(this.newVar);
  }
}
