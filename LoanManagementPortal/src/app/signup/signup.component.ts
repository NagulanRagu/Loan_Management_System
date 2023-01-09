import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Address } from '../model/address';
import { BorrowerDetails } from '../model/borrower-details';
import { LoginServiceService } from '../service/login-service.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  constructor(private router: Router, 
              private loginService: LoginServiceService,
              public borrowerDetails: BorrowerDetails,
              public borrowerAddress: Address) { }

  ngOnInit(): void {
    this.borrowerDetails = new BorrowerDetails();
    this.borrowerDetails.borrowerAddress = new Address();
  }

  signup(){
    this.loginService.signup(this.borrowerDetails).subscribe(
      data => {
        console.log(data);
        this.router.navigate(['login']);
      },
      error => {
        console.log(error);
        this.router.navigate(['signup']);
      })
  }
}
