import { Component, OnInit } from '@angular/core';
import { LoanDetails } from '../model/loan-details';
import { LoanDetailsService } from '../service/loan-details.service';

@Component({
  selector: 'app-loan-details',
  templateUrl: './loan-details.component.html',
  styleUrls: ['./loan-details.component.css']
})
export class LoanDetailsComponent implements OnInit {

  constructor(public loanDetailsService: LoanDetailsService) { }

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
      })
  }
}
