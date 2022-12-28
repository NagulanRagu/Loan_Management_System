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

  getLoanDetailsById(id: number) {
    return this.http.get<LoanDetails>(`${this.baseUrl}loan-by-id/${id}`);
  }

  addLoanDetails(loanDetails: LoanDetails) {
    return this.http.post<LoanDetails>(`${this.baseUrl}/add-loan`,loanDetails);
  }

  updateLoanDetails(id: number,loanDetails: LoanDetails) {
    return this.http.put<LoanDetails>(`${this.baseUrl}/update-loan/${id}`, loanDetails);
  }

  deleteLoanDetails(id: number) {
    return this.http.delete(`${this.baseUrl}/delete-loan/${id}`);
  }

  sendLoanRegistration(loanRegistration: LoanRegistration) {
    return this.http.post<LoanRegistration>(`${this.baseUrl}/apply`, loanRegistration);
  }
}
