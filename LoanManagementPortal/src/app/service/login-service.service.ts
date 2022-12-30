import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map } from 'rxjs';
import { BorrowerDetails } from '../model/borrower-details';
import { HttpCall } from '../model/http-call';
import { LoginCredentails } from '../model/login-credentails';

@Injectable({
  providedIn: 'root'
})
export class LoginServiceService {

  constructor(private http: HttpClient) { }

  private baseUrl = 'http://localhost:8080';

  login(loginCredentails: LoginCredentails) {

    return this.http.post<HttpCall>(`${this.baseUrl}/login-call`, loginCredentails)
            .pipe(map(
              data => {
                sessionStorage.setItem('Authenticated User', loginCredentails.uname);
                return data;
              }
            ));
  }

  signup(borrowerDetails: BorrowerDetails) {

    return this.http.post<BorrowerDetails>(`${this.baseUrl}/signup`, borrowerDetails);
  }

  isUserLoggedIn(): boolean {
    let user = sessionStorage.getItem('Authenticated User');
    return user!=null;
  }

  getUserName() {
    return sessionStorage.getItem('Authenticated User');
  }

  logout(): void {
    sessionStorage.removeItem('Authenticated User');
  }
}
