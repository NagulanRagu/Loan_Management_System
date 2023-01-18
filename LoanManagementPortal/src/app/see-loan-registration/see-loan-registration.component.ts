import { Component, OnInit } from '@angular/core';
import { LoanRegistration } from '../model/loan-registration';
import { LoanRegistrationService } from '../service/loan-registration.service';
import { LoginServiceService } from '../service/login-service.service';

@Component({
  selector: 'app-see-loan-registration',
  templateUrl: './see-loan-registration.component.html',
  styleUrls: ['./see-loan-registration.component.css']
})
export class SeeLoanRegistrationComponent implements OnInit {

  constructor(private loanRegistrationService: LoanRegistrationService,
    private loginService: LoginServiceService) { }

  loanRegistrations!: LoanRegistration[];
  filteredLoanRegistration!: LoanRegistration[];
  borrowerName!: any;
  roles!: any;
  adminRole!: boolean;
  private _searchName!: string;
  get searchName(): string {
    return this._searchName;
  }

  set searchName(value: string) {
    this._searchName = value;
    this.filteredLoanRegistration = this.filter(value);
  }

  ngOnInit(): void {
    this.refreshRegistrationPage();
  }

  refreshRegistrationPage() {
    this.isUserAdmin();
    if(this.adminRole) {
      this.getRegistratedLoan();
    }else {
      this.getRegistratedLoanByName();
    }
  }

  getRegistratedLoan() {
    this.loanRegistrationService.getAll().subscribe(
      data => {
        console.log(data);
        this.loanRegistrations = data;
        this.filteredLoanRegistration = this.loanRegistrations;
      },
      error => {
        console.log(error);
      });
  }

  getRegistratedLoanByName() {
    this.loanRegistrationService.getByBorrowerName(this.borrowerName).subscribe(
      data => {
        console.log(data);
        this.loanRegistrations = data;
        this.filteredLoanRegistration = this.loanRegistrations;
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
      this.borrowerName = this.loginService.getUserName();
      this.adminRole = false;
    }
  }

  filter(searchString: string): LoanRegistration[] {
    return this.loanRegistrations.filter(loanRegistration => 
      loanRegistration.borrowerName.indexOf(searchString)!==-1);
  }
}
