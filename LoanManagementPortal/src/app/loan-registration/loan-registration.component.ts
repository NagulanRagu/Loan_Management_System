import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoanRegistration } from '../model/loan-registration';
import { LoanDetailsService } from '../service/loan-details.service';

@Component({
  selector: 'app-loan-registration',
  templateUrl: './loan-registration.component.html',
  styleUrls: ['./loan-registration.component.css']
})
export class LoanRegistrationComponent implements OnInit {

  constructor(public loanRegistration: LoanRegistration, private loanDetailsService: LoanDetailsService,
    private router: Router) { }

  ngOnInit(): void {
    this.loanRegistration = new LoanRegistration();
  }

  sendOnClick() {

    this.loanDetailsService.sendLoanRegistration(this.loanRegistration).subscribe(
      data => {
        console.log(data);
        this.router.navigate(['registration-success']);
      },
      error => {
        console.log(error);
      });
  }
}
