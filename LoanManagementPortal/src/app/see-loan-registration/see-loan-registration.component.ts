import { Component, OnInit } from '@angular/core';
import { LoanRegistration } from '../model/loan-registration';
import { LoanRegistrationService } from '../service/loan-registration.service';

@Component({
  selector: 'app-see-loan-registration',
  templateUrl: './see-loan-registration.component.html',
  styleUrls: ['./see-loan-registration.component.css']
})
export class SeeLoanRegistrationComponent implements OnInit {

  constructor(private loanRegistrationService: LoanRegistrationService) { }
  
  loanRegistration!: LoanRegistration[];

  ngOnInit(): void {
    this.refreshRegistrationPage();
  }

  refreshRegistrationPage() {
    this.loanRegistrationService.getAll().subscribe(
      data => {
        console.log(data);
        this.loanRegistration = data;
      },
      error => {
        console.log(error);
      });
  }
}
