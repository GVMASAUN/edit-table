import { Injectable } from '@angular/core';
import { Student } from './student';

@Injectable({
  providedIn: 'root'
})
export class DemodataService {
  demoObject: Student | undefined
  constructor() {
    this.demoObject = {
      name: "Gautam Verma",
      class: 12
    }
  }

  getDemoData(): Student | undefined {
    return this.demoObject;
  }
}
