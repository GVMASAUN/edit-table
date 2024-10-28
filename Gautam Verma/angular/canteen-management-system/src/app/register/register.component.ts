import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators, FormControl } from '@angular/forms';
import { User } from '../user';
import { RegisterService } from '../register.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {
  registerForm: FormGroup;
  user: User;
  response: any;

  constructor(private formBuilder: FormBuilder, private registerService: RegisterService, private route: Router) {
    this.registerForm = this.formBuilder.group({
      name: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required],
      role: ['ROLE_USER', Validators.required],
      phoneNumber: ['', [Validators.required, Validators.minLength(10), Validators.maxLength(10)]]
    });
  }

  get email() {
    return this.registerForm.controls['email'] as FormControl;
  }

  registerUser(): void {
    this.user = this.registerForm.value;

    this.registerService.registerUser(this.user).subscribe(resp => {
      console.log(resp);
      this.response = resp;
    });
    if (this.response?.error == null) {
      this.route.navigate(['/login']);
    }
  }
}
