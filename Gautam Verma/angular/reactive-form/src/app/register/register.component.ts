import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';


@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {
  myForm: FormGroup;
  isSubmitted: boolean;

  constructor(private formBuilder: FormBuilder) {
    this.myForm = this.formBuilder.group({
      name: ['', Validators.required],
      password: ['', [Validators.required, Validators.minLength(3)]],
      gender: ['male', Validators.required],
      course: [],
      cars: [],
      date: []
    });
  }

  get name() {
    return this.myForm.controls["name"];
  }

  get password() {
    return this.myForm.controls["password"];
  }

  getFormValues() {
    this.isSubmitted = true;
  }
}
