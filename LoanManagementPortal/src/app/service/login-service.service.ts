import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BorrowerDetails } from '../model/borrower-details';
import { LoginCredentails } from '../model/login-credentails';

@Injectable({
  providedIn: 'root'
})
export class LoginServiceService {

  constructor(private http: HttpClient) { }

  private baseUrl = 'http://localhost:8080/user';

  login(loginCredentails: LoginCredentails) {

    return this.http.post<string>(`${this.baseUrl}/login`, loginCredentails);
  }

  signup(borrowerDetails: BorrowerDetails) {

    return this.http.post<BorrowerDetails>(`${this.baseUrl}/signup`, borrowerDetails);
  }
}
