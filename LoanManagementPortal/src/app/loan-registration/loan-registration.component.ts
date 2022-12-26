import { Component, OnInit } from '@angular/core';
import { LoanRegistration } from '../model/loan-registration';

@Component({
  selector: 'app-loan-registration',
  templateUrl: './loan-registration.component.html',
  styleUrls: ['./loan-registration.component.css']
})
export class LoanRegistrationComponent implements OnInit {

  constructor(public loanRegistration: LoanRegistration) { }

  ngOnInit(): void {
    this.loanRegistration = new LoanRegistration();
  }

}
