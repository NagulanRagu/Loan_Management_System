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

  constructor(private router: Router, 
    private loanRegistrationService: LoanRegistrationService,
    private borrowerDetailsService: BorrowerDetailsService, 
    private loginService: LoginServiceService,
    private loanDetailsService: LoanDetailsService) { }

  
  borrowerDetails!: BorrowerDetails;
  borrowerAddress!: Address;
  loanRegistration!: LoanRegistration; 
  guarantorInfo!: GuarantorInfo;
  guarantorAddress!: GuarantorAddress;
  errorMessage!: any;
  uname!: any;
  loanDetails!: LoanDetails[];
  roles!: any;
  adminRole!: boolean;
  private _borrowerName!: string;
  get borrowerName(): string {
    return this._borrowerName;
  }

  set borrowerName(value: string) {
    this._borrowerName = value;
    this.getBorrowerDetails(value);
  }

  ngOnInit(): void {
    this.loanRegistration = new LoanRegistration();
    this.loanRegistration.guarantorInfo = new GuarantorInfo();
    this.loanRegistration.guarantorInfo.guarantorAddress = new GuarantorAddress();
    this.borrowerDetails = new BorrowerDetails();
    this.borrowerDetails.borrowerAddress = new Address();
    this.isUserAdmin();
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
      this.uname = this.loginService.getUserName();
      this.getBorrowerDetails(this.uname);
    }
  }

  getLoanDetails() {
    this.loanDetailsService.getLoanDetails().subscribe(
      data => {
        console.log(data);
        this.loanDetails = data;
      },
      error => {
        console.log(error);
        this.router.navigate(['internal-server-error']);
      }
    )
  }

  getBorrowerDetails(value: string) {
    this.borrowerDetailsService.getBorrowerDetails(value).subscribe(
      data => {
        console.log(data);
        this.borrowerDetails = data;
        this.errorMessage = null;
      },
      error => {
        console.log(error);
        this.errorMessage = error.error;
      });
  }

  sendOnClick() {
    if(!this.adminRole) {
      this.loanRegistration.status = "Pending";
    }else {
      this.loanRegistration.status = "Accepted";
    }
    this.loanRegistration.borrowerName = this.borrowerDetails.uname;
    console.log(this.loanRegistration);
    this.loanRegistrationService.sendLoanRegistration(this.loanRegistration).subscribe(
      data => {
        console.log(data);
        this.router.navigate(['registration-success']);
      },
      error => {
        console.log(error);
        this.router.navigate(['internal-server-error']);
      });
  }
}
