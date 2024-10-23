import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
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
  submitFlag: boolean = false;

  constructor(private formBuilder: FormBuilder, private validatorService: ValidatorService, private dataService: DataService) {
    this.studentForm = this.formBuilder.group({
      name: ['', [Validators.required, validatorService.nameValidator()]],
      username: ['', [Validators.required, validatorService.usernameValidator()]],
      gender: ['', Validators.required],
      address: ['', [Validators.required, validatorService.addressValidator()]],
      age: ['', [Validators.required, validatorService.ageValidator()]],
      email: ['', [Validators.required, Validators.email, validatorService.emailValidator()]],
      subjects: this.formBuilder.array([], { validators: validatorService.minimumSubjectValidator() })
    });
  }

  get subjects() {
    return this.studentForm.controls['subjects'] as FormArray;
  }

  getFormControl(controlName: string): FormControl {
    return this.studentForm.controls[controlName] as FormControl;
  }

  private createSubject(): FormGroup {
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
      this.submitFlag = false;
      alert("Please enter correct data.")
    } else {
      this.submitFlag = true;
    }
  }

  reset(): void {
    this.isSubmitted = false;
    this.studentForm.reset();
  }

  ngOnInit(): void {
    this.allSubjects = this.dataService.getSubjects();
  }
}
