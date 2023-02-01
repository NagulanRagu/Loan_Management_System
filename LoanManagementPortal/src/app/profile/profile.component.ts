import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Address } from '../model/address';
import { BorrowerDetails } from '../model/borrower-details';
import { ResponseFile } from '../model/response-file';
import { BorrowerDetailsService } from '../service/borrower-details.service';
import { BorrowerDocumentServiceService } from '../service/borrower-document-service.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  constructor(private borrowerDetailsService: BorrowerDetailsService, private route: ActivatedRoute,
              private borrowerDocumentService: BorrowerDocumentServiceService) { }

  uname!: string;
  borrowerDetails!: BorrowerDetails;
  aadhaarFile!: ResponseFile;
  panCardFile!: ResponseFile;

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
        this.borrowerDocumentService.downloadFileByBorrowerName(data.uname).subscribe(
          data => {
            console.log(data);
            data.forEach(element => {
              if(element.fileDetail == "AadhaarCard") {
                this.aadhaarFile = element;
              }else {
                this.panCardFile = element;
              }
            });
          },
          error => {
            console.log(error.error);
          }
        )
      },
      error => {
        console.log(error);
      });
  }
}
