import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { GuarantorAddress } from 'src/app/model/guarantor-address';
import { GuarantorInfo } from 'src/app/model/guarantor-info';
import { LoanDetails } from 'src/app/model/loan-details';
import { LoanRegistration } from 'src/app/model/loan-registration';
import { LoanDetailsService } from 'src/app/service/loan-details.service';
import { LoanRegistrationService } from 'src/app/service/loan-registration.service';
import { LoginServiceService } from 'src/app/service/login-service.service';

@Component({
  selector: 'app-register-loan-details',
  templateUrl: './register-loan-details.component.html',
  styleUrls: ['./register-loan-details.component.css']
})
export class RegisterLoanDetailsComponent implements OnInit {

  constructor(private router: Router, 
    private loanRegistrationService: LoanRegistrationService,
    private loginService: LoginServiceService,
    private loanDetailsService: LoanDetailsService,
    public loanRegistration: LoanRegistration) { }

  loanDetails!: LoanDetails[];
  roles!: any;
  adminRole!: boolean;

  ngOnInit(): void {
    this.loanRegistration = new LoanRegistration;
    this.loanRegistration.guarantorInfo = new GuarantorInfo;
    this.loanRegistration.guarantorInfo.guarantorAddress = new GuarantorAddress;
    this.getLoanDetails();
  }

  isUserAdmin() {
    if (this.loginService.isUserLoggedIn()) {
      this.roles = this.loginService.getRoles();
    }
    if (this.roles.includes("ROLE_ADMIN")) {
      this.adminRole = true;
    } else {
      this.adminRole = false;
    }
  }

  getLoanDetails() {
    this.loanDetailsService.getLoanDetails().subscribe(
      data => {
        console.log(data);
        this.loanDetails = data;
      },
      error => {
        console.log(error.error);
      }
    )
  }

  getRateOfInterest(loanType: string) {
    let rateOfInterest: any
    this.loanDetails.forEach(function(loanDetail){
      if(loanDetail.loanType == loanType){
        rateOfInterest = loanDetail.rateOfInterest
      }
    })
    return rateOfInterest;
  }

  emiCalculation(providedLoanAmt: string, n: number, loanType: string) {
    let p: number = Number(providedLoanAmt);
    let r: number = this.getRateOfInterest(loanType)/(12*100);
    let emi: number = Math.round((p*r*Math.pow(1+r,n))/(Math.pow(1+r,n)-1));
    this.loanRegistration.emiAmt = String(emi);
  }

  submit() {

  }
}
