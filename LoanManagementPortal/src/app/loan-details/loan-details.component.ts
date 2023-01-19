import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoanDetails } from '../model/loan-details';
import { LoanDetailsService } from '../service/loan-details.service';

@Component({
  selector: 'app-loan-details',
  templateUrl: './loan-details.component.html',
  styleUrls: ['./loan-details.component.css']
})
export class LoanDetailsComponent implements OnInit {

  constructor(public loanDetailsService: LoanDetailsService, private router: Router) { }

  loanDetails!: LoanDetails[];

  ngOnInit(): void {
    this.refreshPage();
  }

  refreshPage() {
    this.loanDetailsService.getLoanDetails().subscribe(
      data => {
        console.log(data);
        this.loanDetails = data;
      },
      error => {
        console.log(error);
        this.router.navigate(['internal-server-error']);
      });
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
