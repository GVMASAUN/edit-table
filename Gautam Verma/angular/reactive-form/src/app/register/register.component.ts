import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators, FormArray } from '@angular/forms';


@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {
  myForm: FormGroup;
  isSubmitted: boolean = false;

  constructor(private formBuilder: FormBuilder) {
    this.myForm = this.formBuilder.group({
      name: ['', Validators.required],
      password: ['', [Validators.required, Validators.minLength(3)]],
      gender: ['male', Validators.required],
      course: [],
      cars: [],
      date: [],
      qualifications: this.formBuilder.array([])
    });
  }

  get name() {
    return this.myForm.controls["name"];
  }

  get password() {
    return this.myForm.controls["password"];
  }

  get qualifications() {
    return this.myForm.controls["qualifications"] as FormArray;
  }

  private createQualification(): FormGroup {
    return this.formBuilder.group({
      course: [''],
      percentage: ['']
    });
  }

  addQualification(): void {
    this.qualifications.push(this.createQualification());
  }

  removeQualification(index: number): void {
    this.qualifications.removeAt(index);
  }

  getFormValues(): void {
    this.isSubmitted = true;
  }
}
