import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { LoanDetails } from '../model/loan-details';

@Injectable({
  providedIn: 'root'
})
export class LoanDetailsService {

  constructor(private http: HttpClient) { }

  private baseUrl = 'http://localhost:8090/loan';

  getLoanDetails() {

    return this.http.get<LoanDetails[]>(`${this.baseUrl}/`);
  }
}
