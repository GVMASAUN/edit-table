import { Injectable } from '@angular/core';
import { AbstractControl, FormArray, FormControl, FormGroup, ValidationErrors, ValidatorFn } from '@angular/forms';
import { DataService } from './data.service';

@Injectable({
  providedIn: 'root'
})
export class ValidatorService {
  constructor(private dataService: DataService) { }

  minimumSubjectValidator(): ValidatorFn {
    return (control: AbstractControl): ValidationErrors | null => {
      let invalidSize: boolean = false;
      if ((control as FormArray).length < 5) {
        invalidSize = true;
      } else {
        invalidSize = false;
      }
      return invalidSize ? { invalidSize: true } : null;
    }
  }

  ageValidator(): ValidatorFn {
    return (control: AbstractControl): ValidationErrors | null => {
      const value = control.value;

      let isValid: Boolean;

      if (value > 20 && value < 30) {
        isValid = true;
      } else {
        isValid = false;
      }

      return isValid ? null : { ageRange: true };
    }
  }

  nameValidator(): ValidatorFn {
    return (control: AbstractControl): ValidationErrors | null => {
      const value = control.value;

      const hasAlphabets = /^[A-Za-z ]+$/.test(value);

      return !hasAlphabets ? { noAlphabet: true } : null;
    }
  }

  emailValidator(): ValidatorFn {
    return (control: AbstractControl): ValidationErrors | null => {
      const email: string = control.value;
      let isValidDomain: Boolean;
      let domainName = email?.substring(email.lastIndexOf('@') + 1);

      if (domainName.length == 0) {
        return null;
      }

      if (domainName === this.dataService.domainName) {
        isValidDomain = true;
      } else {
        isValidDomain = false;
      }

      return isValidDomain ? null : { invalidDomain: true };
    }
  }

  addressValidator(): ValidatorFn {
    return (control: AbstractControl): ValidationErrors | null => {
      let address: string = control.value;
      let minLength: boolean = false;
      let maxLength: boolean = false;
      let isError: boolean = false;

      address = address.trim();

      if (address.length < 10) {
        minLength = true;
      } else if (address.length > 20) {
        maxLength = true;
      }

      isError = minLength || maxLength;

      return isError ? { minError: minLength, maxError: maxLength } : null;
    }
  }

  usernameValidator(): ValidatorFn {
    return (control: AbstractControl): ValidationErrors | null => {
      const username: string = control.value;
      let isValidUsername: Boolean;

      if (this.dataService.userNames.includes(username)) {
        isValidUsername = false;
      } else {
        isValidUsername = true;
      }
      return isValidUsername ? null : { existingUsername: true };
    }
  }

  subjectValidator(studentForm: FormGroup): ValidatorFn {
    return (control: AbstractControl): ValidationErrors | null => {
      const subjectCode: string = control.value;
      let isRepeated: boolean = false;
      let formGroups: AbstractControl[] = (studentForm.controls["subjects"] as FormArray)?.controls;

      for (let formGroupIndex = 0; formGroupIndex < formGroups.length - 1; ++formGroupIndex) {
        let formGroup = formGroups[formGroupIndex] as FormGroup;

        if (formGroup.value["subjectcode"] == subjectCode) {
          isRepeated = true;
          break;
        } else {
          isRepeated = false;
        }
      }
      return !isRepeated ? null : { repeatedSubjects: true };
    }
  }

}
