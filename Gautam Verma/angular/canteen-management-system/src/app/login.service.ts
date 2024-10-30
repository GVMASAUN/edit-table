import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, tap } from 'rxjs';
import { User } from './user';
import { UserService } from './user.service';

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  private authenticated = new BehaviorSubject<boolean>(false);
  private admin = new BehaviorSubject<boolean>(false);
  private user = new BehaviorSubject<boolean>(false);

  isAuthenticated$ = this.authenticated.asObservable();
  isAdmin$ = this.admin.asObservable();
  isUser$ = this.user.asObservable();

  constructor(private http: HttpClient, private userService: UserService) { }

  login(user: User): Observable<any> {
    return this.http.post<User>("http://localhost:8080/login", user).pipe(
      tap(response => {
        const isAdmin = response.role === 'ROLE_ADMIN';
        const isUser = response.role === 'ROLE_USER';

        this.authenticated.next(true);
        this.admin.next(isAdmin);
        this.user.next(isUser);

      })
    );
  }

  logout() {
    this.userService.logout();
    this.authenticated.next(false);
    this.admin.next(false);
    this.user.next(false);
  }
}
