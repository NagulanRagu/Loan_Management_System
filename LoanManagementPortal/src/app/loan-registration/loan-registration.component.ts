import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Address } from '../model/address';
import { BorrowerDetails } from '../model/borrower-details';
import { GuarantorAddress } from '../model/guarantor-address';
import { GuarantorInfo } from '../model/guarantor-info';
import { LoanDetails } from '../model/loan-details';
import { LoanRegistration } from '../model/loan-registration';
import { BorrowerDetailsService } from '../service/borrower-details.service';
import { LoanDetailsService } from '../service/loan-details.service';
import { LoanRegistrationService } from '../service/loan-registration.service';
import { LoginServiceService } from '../service/login-service.service';

@Component({
  selector: 'app-loan-registration',
  templateUrl: './loan-registration.component.html',
  styleUrls: ['./loan-registration.component.css']
})
export class LoanRegistrationComponent implements OnInit {

  constructor(
    private router: Router, 
    private loanRegistrationService: LoanRegistrationService,
    private borrowerDetailsService: BorrowerDetailsService, 
    private loginService: LoginServiceService,
    private loanDetailsService: LoanDetailsService,
    public borrowerDetails: BorrowerDetails,
    public borrowerAddress: Address,
    public loanRegistration: LoanRegistration, 
    public guarantorInfo: GuarantorInfo,
    public guarantorAddress: GuarantorAddress) { }

  uname!: any;
  loanDetails!: LoanDetails[];
  roles!: any;
  adminRole!: boolean;

  ngOnInit(): void {
    this.loanRegistration = new LoanRegistration();
    this.loanRegistration.guarantorInfo = new GuarantorInfo();
    this.loanRegistration.guarantorInfo.guarantorAddress = new GuarantorAddress();
    this.borrowerDetails = new BorrowerDetails();
    this.borrowerDetails.borrowerAddress = new Address();
    this.whenToGetBorrowerDetails();
    this.getLoanDetails();
    this.isUserAdmin();
  }

  whenToGetBorrowerDetails() {
    if(this.adminRole) {
      this.uname = this.borrowerDetails.uname;
      this.getBorrowerDetails();
    }else {
      this.uname = this.loginService.getUserName();
      this.getBorrowerDetails();
    }
  }

  getBorrowerDetails() {
    this.borrowerDetailsService.getBorrowerDetails(this.uname).subscribe(
      data => {
        console.log(data);
        this.borrowerDetails = data;
        console.log(this.loanRegistration);
      });
  }

  getLoanDetails() {
    this.loanDetailsService.getLoanDetails().subscribe(
      data => {
        console.log(data);
        this.loanDetails = data;
      },
      error => {
        console.log(error);
      }
    )
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

  sendOnClick() {
    if(!this.adminRole) {
      this.loanRegistration.status = "Pending";
    }else {
      this.loanRegistration.status = "Accepted";
    }
    this.loanRegistration.borrowerName = this.borrowerDetails.uname;
    this.loanRegistrationService.sendLoanRegistration(this.loanRegistration).subscribe(
      data => {
        console.log(data);
        this.router.navigate(['registration-success']);
      },
      error => {
        console.log(error);
      });
  }
}
