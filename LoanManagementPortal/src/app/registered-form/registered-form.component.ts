import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Address } from '../model/address';
import { BorrowerDetails } from '../model/borrower-details';
import { GuarantorAddress } from '../model/guarantor-address';
import { GuarantorInfo } from '../model/guarantor-info';
import { LoanRegistration } from '../model/loan-registration';
import { BorrowerDetailsService } from '../service/borrower-details.service';
import { LoanRegistrationService } from '../service/loan-registration.service';

@Component({
  selector: 'app-registered-form',
  templateUrl: './registered-form.component.html',
  styleUrls: ['./registered-form.component.css']
})
export class RegisteredFormComponent implements OnInit {

  constructor(private route: ActivatedRoute,
    private loanRegistrationService: LoanRegistrationService,
    private borrowerDetailsService: BorrowerDetailsService,
    public loanRegistration: LoanRegistration,
    public guarantorInfo: GuarantorInfo,
    public guarantorAddress: GuarantorAddress,
    public borrowerDetails: BorrowerDetails,
    public borrowerAddress: Address) { }

  id!: number;
  borrowerName!: string;

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];
    this.loanRegistration = new LoanRegistration();
    this.loanRegistration.guarantorInfo = new GuarantorInfo();
    this.loanRegistration.guarantorInfo.guarantorAddress = new GuarantorAddress();
    this.borrowerDetails = new BorrowerDetails();
    this.borrowerDetails.borrowerAddress = new Address();
    this.getDetails();
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
}
