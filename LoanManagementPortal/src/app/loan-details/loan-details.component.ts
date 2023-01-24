import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoanDetails } from '../model/loan-details';
import { LoanDetailsService } from '../service/loan-details.service';
import { LoginServiceService } from '../service/login-service.service';

@Component({
  selector: 'app-loan-details',
  templateUrl: './loan-details.component.html',
  styleUrls: ['./loan-details.component.css']
})
export class LoanDetailsComponent implements OnInit {

  constructor(public loanDetailsService: LoanDetailsService, private router: Router,
    private loginService: LoginServiceService) { }

  loanDetails!: LoanDetails[];
  errorMessage!: any;
  roles!: any;
  adminRole!: boolean;

  ngOnInit(): void {
    this.refreshPage();
    this.isUserAdmin();
  }

  refreshPage() {
    this.loanDetailsService.getLoanDetails().subscribe(
      data => {
        console.log(data);
        this.loanDetails = data;
        this.errorMessage = null;
      },
      error => {
        console.log(error.error);
        if (error.error == "No Details Present") {
          this.errorMessage = error.error;
        } else {
          this.router.navigate(['internal-server-error']);
        }
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

  addLoanDetails() {
    this.router.navigate(['update-loan', -1]);
  }

  updateLoanDetails(id: number) {
    this.router.navigate(['update-loan', id]);
  }

  deleteLoanDetails(id: number) {
    this.loanDetailsService.deleteLoanDetails(id).subscribe(
      response => {
        this.refreshPage();
      });
  }
}
