import { Component, OnInit } from '@angular/core';
import { BorrowerDetails } from '../model/borrower-details';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  constructor() { }

  public borrowerDetails!: BorrowerDetails;

  ngOnInit(): void {
    this.borrowerDetails = new BorrowerDetails("","","","","","","");
  }

  signup(){}
}
