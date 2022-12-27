import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { LoanDetails } from '../model/loan-details';
import { LoanRegistration } from '../model/loan-registration';

@Injectable({
  providedIn: 'root'
})
export class LoanDetailsService {

  constructor(private http: HttpClient) { }

  private baseUrl = 'http://localhost:8090';

  getLoanDetails() {

    return this.http.get<LoanDetails[]>(`${this.baseUrl}/all-loan`);
  }

  sendLoanRegistration(loanRegistration: LoanRegistration) {

    return this.http.post<LoanRegistration>(`${this.baseUrl}/apply`, loanRegistration);
  }
}
