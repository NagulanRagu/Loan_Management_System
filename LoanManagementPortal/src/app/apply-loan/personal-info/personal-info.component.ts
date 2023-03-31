import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Address } from 'src/app/model/address';
import { BorrowerDetails } from 'src/app/model/borrower-details';
import { PersonalInformation } from 'src/app/model/personal-information';
import { BorrowerDetailsService } from 'src/app/service/borrower-details.service';

@Component({
  selector: 'app-personal-info',
  templateUrl: './personal-info.component.html',
  styleUrls: ['./personal-info.component.css']
})
export class PersonalInfoComponent implements OnInit {

  constructor(private router: Router, private borrwerDetailsService: BorrowerDetailsService, public borrowerDetails: BorrowerDetails) { }

  ngOnInit(): void {
    this.borrowerDetails.personalInformation = new PersonalInformation();
    this.borrowerDetails.borrowerAddress = new Address();
  }

  save() {
    this.borrwerDetailsService.updateBorrowerDetails(this.borrowerDetails).subscribe(
      data => {
        console.log(data);
      },
      error => {
        console.log(error.error);
      }
    )
  }

  next() {
    
  }

}
