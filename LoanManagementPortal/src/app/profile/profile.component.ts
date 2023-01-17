import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Address } from '../model/address';
import { BorrowerDetails } from '../model/borrower-details';
import { BorrowerDetailsService } from '../service/borrower-details.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  constructor(private borrowerDetailsService: BorrowerDetailsService, private route: ActivatedRoute) { }

  uname!: string;
  borrowerDetails!: BorrowerDetails;

  ngOnInit(): void {
    this.uname = this.route.snapshot.params['uname'];
    this.borrowerDetails = new BorrowerDetails;
    this.borrowerDetails.borrowerAddress = new Address;
    this.getDetails();
  }

  getDetails() {
    this.borrowerDetailsService.getBorrowerDetails(this.uname).subscribe(
      data => {
        console.log(data);
        this.borrowerDetails = data;
      },
      error => {
        console.log(error);
      });
  }
}
