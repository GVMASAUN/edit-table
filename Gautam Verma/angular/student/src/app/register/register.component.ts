import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ValidatorService } from '../validator.service';
import { DataService } from '../data.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  studentForm: FormGroup;
  isSubmitted: boolean;
  allSubjects: any = {};

  constructor(private formBuilder: FormBuilder, private validatorService: ValidatorService, private dataService: DataService) {
    this.studentForm = this.formBuilder.group({
      name: ['', [Validators.required, validatorService.nameValidator()]],
      username: ['', [Validators.required, validatorService.usernameValidator()]],
      gender: ['', Validators.required],
      address: ['', [Validators.required, validatorService.addressValidator()]],
      age: ['', [Validators.required, validatorService.ageValidator()]],
      email: ['', [Validators.required, Validators.email, validatorService.emailValidator()]],
      subjects: this.formBuilder.array([])
    });
  }

  get subjects() {
    return this.studentForm.controls['subjects'] as FormArray;
  }

  get name() {
    return this.studentForm.controls['name'];
  }

  get username() {
    return this.studentForm.controls['username'];
  }

  get address() {
    return this.studentForm.controls['address'];
  }

  get email() {
    return this.studentForm.controls['email'];
  }

  get gender() {
    return this.studentForm.controls['gender'];
  }

  get age() {
    return this.studentForm.controls['age'];
  }

  createSubject(): FormGroup {
    return this.formBuilder.group({
      subjectcode: ['', [Validators.required, this.validatorService.subjectValidator(this.studentForm)]],
      optional: []
    });
  }

  addSubject(): void {
    this.subjects.push(this.createSubject());
  }

  removeSubject(index: number): void {
    this.subjects.removeAt(index);
  }

  formSubmit(): void {
    this.isSubmitted = true;
    if (this.studentForm.invalid) {
      alert("Please enter correct data.")
    }
  }

  reset(): void {
    this.studentForm.reset();
    this.isSubmitted = false;
  }

  ngOnInit(): void {
    this.allSubjects = this.dataService.getSubjects();
  }
}
