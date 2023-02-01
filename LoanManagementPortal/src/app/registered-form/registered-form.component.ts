import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Address } from '../model/address';
import { BorrowerDetails } from '../model/borrower-details';
import { GuarantorAddress } from '../model/guarantor-address';
import { GuarantorInfo } from '../model/guarantor-info';
import { LoanDetails } from '../model/loan-details';
import { LoanRegistration } from '../model/loan-registration';
import { ResponseFile } from '../model/response-file';
import { BorrowerDetailsService } from '../service/borrower-details.service';
import { BorrowerDocumentServiceService } from '../service/borrower-document-service.service';
import { LoanDetailsService } from '../service/loan-details.service';
import { LoanRegistrationService } from '../service/loan-registration.service';
import { LoginServiceService } from '../service/login-service.service';

@Component({
  selector: 'app-registered-form',
  templateUrl: './registered-form.component.html',
  styleUrls: ['./registered-form.component.css']
})
export class RegisteredFormComponent implements OnInit {

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private loanRegistrationService: LoanRegistrationService,
    private borrowerDetailsService: BorrowerDetailsService,
    private loginService: LoginServiceService,
    private loanDetailsService: LoanDetailsService,
    private borrowerDocumentService: BorrowerDocumentServiceService
    ) { }

  loanRegistration!: LoanRegistration
  guarantorInfo!: GuarantorInfo
  guarantorAddress!: GuarantorAddress
  borrowerDetails!: BorrowerDetails
  borrowerAddress!: Address
  loanDetails!: LoanDetails
  id!: number;
  borrowerName!: string;
  roles!: any;
  adminRole!: boolean;
  aadhaarFile!: ResponseFile;
  panCardFile!: ResponseFile;

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];
    this.loanRegistration = new LoanRegistration();
    this.loanRegistration.guarantorInfo = new GuarantorInfo();
    this.loanRegistration.guarantorInfo.guarantorAddress = new GuarantorAddress();
    this.borrowerDetails = new BorrowerDetails();
    this.borrowerDetails.borrowerAddress = new Address();
    this.loanDetails = new LoanDetails();
    this.getDetails();
    this.isUserAdmin();
  }

  getDetails() {
    this.loanRegistrationService.getById(this.id).subscribe(
      data => {
        console.log(data);
        this.loanRegistration = data;
        this.borrowerDetailsService.getBorrowerDetails(data.borrowerName).subscribe(
          data => {
            console.log(data);
            this.borrowerDetails = data;
          },
          error => {
            console.log(error.error);
          });

        this.borrowerDocumentService.downloadFileByBorrowerName(data.borrowerName).subscribe(
          data => {
            console.log(data);
            data.forEach(element => {
              if(element.fileDetail == "AadhaarCard") {
                this.aadhaarFile = element;
              }else {
                this.panCardFile = element;
              }
            });
          },
          error => {
            console.log(error.error)
          });

        this.loanDetailsService.getLoanDetailByType(data.loanType).subscribe(
          data => {
            console.log(data);
            this.loanDetails = data;
          },
          error => {
            console.log(error.error);
          });
      },
      error => {
        console.log(error.error);
      });
  }

  emiCalculation(providedLoanAmt: string, n: number, rateOfInterest: number) {
    let p: number = Number(providedLoanAmt);
    let r: number = rateOfInterest/(12*100);
    let emi: number = Math.round((p*r*Math.pow(1+r,n))/(Math.pow(1+r,n)-1));
    this.loanRegistration.emiAmt = String(emi);
  }

  loanAccepted() {
    this.loanRegistration.status = "Accepted";
    this.updatingLoan();
  }

  loanRejected() {
    this.loanRegistration.status = "Rejected";
    this.updatingLoan();
  }

  updatingLoan() {
    this.loanRegistrationService.update(this.loanRegistration).subscribe(
      data => {
        console.log(data);
        this.router.navigate(['all-registration']);
      },
      error => {
        console.log(error);
      });
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

  isLoanAcceptedOrRejected(status: string): boolean {
    if(status == "Pending") {
      return true;
    }else {
      return false;
    }
  }

  whenToShow(value: string): boolean {
    if(!this.adminRole) {
      return true;
    }else {
      if(!this.isLoanAcceptedOrRejected(value)) {
          return true;
      }else {
        
        return false;
      }
    }
  }
}
