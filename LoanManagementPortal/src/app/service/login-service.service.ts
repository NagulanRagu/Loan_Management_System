import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BorrowerDetails } from '../model/borrower-details';
import { HttpCall } from '../model/http-call';
import { LoginCredentails } from '../model/login-credentails';
import { LoginStatus } from '../model/login-status';

@Injectable({
  providedIn: 'root'
})
export class LoginServiceService {

  constructor(private http: HttpClient, private loginStatus: LoginStatus) { }

  private baseUrl = 'http://localhost:8080';

  login(loginCredentails: LoginCredentails) {

    return this.http.post<HttpCall>(`${this.baseUrl}/login`, loginCredentails);
  }

  signup(borrowerDetails: BorrowerDetails) {

    return this.http.post<BorrowerDetails>(`${this.baseUrl}/signup`, borrowerDetails);
  }

  setLoginStatus(status: boolean): void {
    this.loginStatus.status = status;
  }

  getLoginStatus(): boolean {
    return this.loginStatus.status;
  }
}
