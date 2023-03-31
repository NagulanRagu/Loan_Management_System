import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { LoanDetails } from '../model/loan-details';
import { LoginServiceService } from './login-service.service';

@Injectable({
  providedIn: 'root'
})
export class LoanDetailsService {

  constructor(private http: HttpClient, private loginService: LoginServiceService) { }

  private baseUrl = 'http://localhost:8090';

  getLoanDetails() {
    return this.http.get<LoanDetails[]>(`${this.baseUrl}/all-loan`);
  }

  getLoanDetailsById(id: number) {
    return this.http.get<LoanDetails>(`${this.baseUrl}/loan-by-id/${id}`);
  }

  getLoanDetailByType(loanType: string) {
    return this.http.get<LoanDetails>(`${this.baseUrl}/loan-by-loanType/${loanType}`)
  }

  addLoanDetails(loanDetails: LoanDetails) {
    return this.http.post<LoanDetails>(`${this.baseUrl}/add-loan`, loanDetails);
  }

  updateLoanDetails(id: number, loanDetails: LoanDetails) {
    return this.http.put<LoanDetails>(`${this.baseUrl}/update-loan/${id}`, loanDetails).pipe();
  }

  deleteLoanDetails(id: number) {
    return this.http.delete(`${this.baseUrl}/delete-loan/${id}`);
  }
}
