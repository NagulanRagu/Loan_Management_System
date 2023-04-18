import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { empty, iif } from 'rxjs';
import { Address } from '../model/address';
import { BorrowerDetails } from '../model/borrower-details';
import { GuarantorAddress } from '../model/guarantor-address';
import { GuarantorInfo } from '../model/guarantor-info';
import { LoanDetails } from '../model/loan-details';
import { LoanRegistration } from '../model/loan-registration';
import { PersonalInformation } from '../model/personal-information';
import { ResponseFile } from '../model/response-file';
import { BorrowerDetailsService } from '../service/borrower-details.service';
import { BorrowerDocumentServiceService } from '../service/borrower-document-service.service';
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
    private loanDetailsService: LoanDetailsService,
    private borrowerDocumentService: BorrowerDocumentServiceService,
    public borrowerDetails: BorrowerDetails,
    public loanRegistration: LoanRegistration) { }

  isPersonalInfo: boolean = true;
  isLoanDetail: boolean = false;
  errorMessage!: any;
  uname!: any;
  loanDetails!: LoanDetails[];
  roles!: any;
  adminRole!: boolean;
  emiErrorMessage!: string;
  // fileDetail: string[] = ["AadhaarCard", "Pancard"];
  // getAadhaarFile!: ResponseFile;
  // getPancardFile!: ResponseFile;
  // aadhaarFile!: File;
  // panCardFile!: File;
  // aadhaarCardResponse!: ResponseFile;
  // pancardCardResponse!: ResponseFile;
  // aadhaarCardUploaded: boolean = false;
  // panCardUploaded: boolean = false;
  // uploadSuccessMessage!: string;
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
    this.borrowerDetails.personalInformation = new PersonalInformation();
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

  personalInfo() {
    this.isPersonalInfo = true;
    this.isLoanDetail = false;
  }

  getBorrowerDetails(value: string) {
    this.borrowerDetailsService.getBorrowerDetails(value).subscribe(
      data => {
        console.log(data);
        this.borrowerDetails = data;
        if(!this.borrowerDetails.personalInformation) {
          this.borrowerDetails.personalInformation = new PersonalInformation();
        }
        if(!this.borrowerDetails.borrowerAddress) {
          this.borrowerDetails.borrowerAddress = new Address();
        }
        // this.isDocumentUploaded(data.uname);
        this.errorMessage = null;
      },
      error => {
        console.log(error);
        this.errorMessage = error.error;
      });
  }

  // onAadhaarFileChange(event: any) {
  //   this.aadhaarFile = event.target.files[0];
  // }

  // onPanCardFileChange(event: any) {
  //   this.panCardFile = event.target.files[0];
  // }

  // onUpload(file: File, borrowerName: string, fileDetail: string) {
  //   this.borrowerDocumentService.uploadFile(file, borrowerName, fileDetail).subscribe(
  //     data => {
  //       console.log(data);
  //       if(data.fileDetail == "AadhaarCard") {
  //         this.aadhaarCardResponse = data;
  //       }else {
  //         this.pancardCardResponse = data;
  //       }
  //       this.uploadSuccessMessage = " File is Uploaded";
  //     },
  //     error => {
  //       console.log(error.error);
  //       this.uploadSuccessMessage = " File is not uploaded";
  //     }
  //   )
  // }

  // isDocumentUploaded(borrowerName: string) {
  //   this.borrowerDocumentService.downloadFileByBorrowerName(borrowerName).subscribe(
  //     data => {
  //       console.log(data);
  //       data.forEach(element => {
  //         if(element.fileDetail == "AadhaarCard") {
  //           this.getAadhaarFile = element;
  //         }else {
  //           this.getPancardFile = element;
  //         }
  //       });
  //     },
  //     error => {
  //       console.log(error.error)
  //     });
  // }

  getLoanDetails() {
    this.loanDetailsService.getLoanDetails().subscribe(
      data => {
        console.log(data);
        this.loanDetails = data;
      },
      error => {
        console.log(error.error);
      })
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

  emiCalculation() {
    let p: number = 0;
    let r: number = 0;
    let n: number = 0;
    if((this.loanRegistration.providedLoanAmt || this.loanRegistration.askedLoanAmt) && this.loanRegistration.loanType && this.loanRegistration.paymentPeriod) {
      this.emiErrorMessage = "";
      if(this.loanRegistration.providedLoanAmt) {
        p = Number(this.loanRegistration.providedLoanAmt);
      }else {
        p = Number(this.loanRegistration.askedLoanAmt);
      }
      r = this.getRateOfInterest(this.loanRegistration.loanType)/(12*100);
      n = this.loanRegistration.paymentPeriod;
      let emi: number = Math.round((p*r*Math.pow(1+r,n))/(Math.pow(1+r,n)-1));
      this.loanRegistration.emiAmt = String(emi);
    }else {
      this.emiErrorMessage = "Please Enter the Loan Amount, Loan Type and Payment Period";
    }
  }

  saveBorrowerDetails() {
    this.borrowerDetailsService.updateBorrowerDetails(this.borrowerDetails).subscribe(
      data => {
        console.log(data);
      },
      error => {
        console.log(error.error);
      }
    )
  }

  save() {
    this.saveBorrowerDetails();
  }

  next() {
    this.isPersonalInfo = false;
    this.isLoanDetail = true;
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
      });
  }
}
