import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BorrowerDetails } from '../model/borrower-details';

@Injectable({
  providedIn: 'root'
})
export class BorrowerDetailsService {

  constructor(private http: HttpClient) { }

  private baseUrl = 'http://localhost:8080';

  getBorrowerDetails(uname: string) {
    return this.http.get<BorrowerDetails>(`${this.baseUrl}/user-by-uname/${uname}`);
  }
}
