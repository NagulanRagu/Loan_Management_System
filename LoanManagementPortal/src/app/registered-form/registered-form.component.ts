import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Address } from '../model/address';
import { BorrowerDetails } from '../model/borrower-details';
import { GuarantorAddress } from '../model/guarantor-address';
import { GuarantorInfo } from '../model/guarantor-info';
import { LoanRegistration } from '../model/loan-registration';
import { BorrowerDetailsService } from '../service/borrower-details.service';
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
    public loanRegistration: LoanRegistration,
    public guarantorInfo: GuarantorInfo,
    public guarantorAddress: GuarantorAddress,
    public borrowerDetails: BorrowerDetails,
    public borrowerAddress: Address
    ) { }

  id!: number;
  borrowerName!: string;
  roles!: any;
  adminRole!: boolean;

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];
    this.loanRegistration = new LoanRegistration();
    this.loanRegistration.guarantorInfo = new GuarantorInfo();
    this.loanRegistration.guarantorInfo.guarantorAddress = new GuarantorAddress();
    this.borrowerDetails = new BorrowerDetails();
    this.borrowerDetails.borrowerAddress = new Address();
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
            console.log(error);
          });
      },
      error => {
        console.log(error);
      });
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
}
