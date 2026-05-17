import { Component, KeyValueDiffers } from '@angular/core';
import { FormBuilder, FormGroup, Validators, FormArray, ValidatorFn, AbstractControl, ValidationErrors } from '@angular/forms';


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
      name: ['', [Validators.required, this.nameValidator()]],
      password: ['', [Validators.required, Validators.minLength(3)]],
      gender: [],
      course: [],
      cars: [],
      date: [],
      qualifications: this.formBuilder.array([])
    });
  }

  nameValidator(): ValidatorFn {
    return (control: AbstractControl): ValidationErrors | null => {
      const value = control.value;
      const hasDigitCase = /[0-9]+/.test(value);

      return hasDigitCase ? { containsDigit: true } : null;
    }
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
