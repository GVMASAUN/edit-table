import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { User } from '../user';
import { LoginService } from '../login.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  loginForm: FormGroup;
  user: User;
  response: any;

  constructor(private formBuilder: FormBuilder, private loginService: LoginService, private router: Router) {
    this.loginForm = this.formBuilder.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required]
    });
  }

  login(): void {
    this.loginService.login(this.loginForm.value).subscribe(response => {
      console.log(response);
      this.response = response;
      if (response?.error == null || response.statusCode == 200) {
        localStorage.setItem('token', response.token)
        localStorage.setItem('role', response.role)
        // this.router.navigate(['/order']);
      } else {
        this.response = response;
      }
    });
  }
}
