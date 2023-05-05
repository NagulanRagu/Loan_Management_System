import { ComponentFixture, TestBed } from '@angular/core/testing';
import { LoanRegistration } from '../model/loan-registration';

import { SeeLoanRegistrationComponent } from './see-loan-registration.component';

describe('SeeLoanRegistrationComponent', () => {
  let component: SeeLoanRegistrationComponent;
  let fixture: ComponentFixture<SeeLoanRegistrationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SeeLoanRegistrationComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SeeLoanRegistrationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

const loanRegistrations: LoanRegistration[] = [
  {
      "id": 1,
      "borrowerName": "Nagulan R U",
      "loanType": "Personal",
      "askedLoanAmt": "4,00,000",
      "providedLoanAmt": "4,00,000",
      "paymentPeriod": 60,
      "emiAmt": "9,727",
      "issuedDate": new Date,
      "guarantorInfo": {
          "id": 1,
          "guarantorName": "Ravikumar K",
          "guarantorPhoneNo": "8012646056",
          "guarantorEmailId": "ravikumaravel00@gmail.com",
          "guarantorAddress": {
              "id": 1,
              "houseNo": "63B-72B",
              "street": "Chinnarasingam Street",
              "city": "Vadasery",
              "state": "TamilNadu",
              "pincode": "629001"
          }
      },
      "status": "Accepted"
  },
  {
      "id": 2,
      "borrowerName": "Nagulan R U",
      "loanType": "House",
      "askedLoanAmt": "40,00,000",
      "providedLoanAmt": "40,00,000",
      "paymentPeriod": 240,
      "emiAmt": "40,000",
      "issuedDate": new Date,
      "guarantorInfo": {
          "id": 2,
          "guarantorName": "Ravikumar K",
          "guarantorPhoneNo": "8012646056",
          "guarantorEmailId": "ravikumaravel00@gmail.com",
          "guarantorAddress": {
              "id": 2,
              "houseNo": "63B-72B",
              "street": "Chinnarasingam Street",
              "city": "Vadasery",
              "state": "TamilNadu",
              "pincode": "629001"
          }
      },
      "status": "Rejected"
  },
  {
      "id": 3,
      "borrowerName": "Nagulan R U",
      "loanType": "Vechile",
      "askedLoanAmt": "10,00,000",
      "providedLoanAmt": "10,00,000",
      "paymentPeriod": 120,
      "emiAmt": "20,000",
      "issuedDate": new Date,
      "guarantorInfo": {
          "id": 3,
          "guarantorName": "Ravikumar K",
          "guarantorPhoneNo": "8012646056",
          "guarantorEmailId": "ravikumaravel00@gmail.com",
          "guarantorAddress": {
              "id": 3,
              "houseNo": "63B-72B",
              "street": "Chinnarasingam Street",
              "city": "Vadasery",
              "state": "TamilNadu",
              "pincode": "629001"
          }
      },
      "status": "Pending"
  }
]