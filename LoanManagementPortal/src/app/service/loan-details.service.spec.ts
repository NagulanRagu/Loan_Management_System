import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { TestBed } from '@angular/core/testing';
import { loanDetails } from '../test-variables/loan-details';

import { LoanDetailsService } from './loan-details.service';

describe('LoanDetailsService', () => {
  let service: LoanDetailsService;
  let httpTestingController: HttpTestingController;
  let baseUrl = 'http://localhost:8090';

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule]
    });
    service = TestBed.inject(LoanDetailsService);
    httpTestingController = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpTestingController.verify();
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should return all loan details', () => {
    service.getLoanDetails().subscribe(
      data => {
        expect(data).toBeTruthy();
        expect(data).toEqual(loanDetails);
        expect(data.length).toEqual(4);
      });
    const req = httpTestingController.expectOne({
      method: "GET",
      url: `${baseUrl}/all-loan`
    });
    req.flush(loanDetails);
  });
  
  it('should return loan details by id', () => {
    service.getLoanDetailsById(2).subscribe(
      data => {
        expect(data).toBeTruthy();
        expect(data).toEqual(loanDetails[1]);
        expect(data.id).toEqual(2);
      });
    const req = httpTestingController.expectOne({
      method: "GET",
      url: `${baseUrl}/loan-by-id/2`
    });
    req.flush(loanDetails[1]);
  });
  
  it('should return loan details by loan type', () => {
    service.getLoanDetailByType('Personal').subscribe(
      data => {
        expect(data).toBeTruthy();
        expect(data).toEqual(loanDetails[0]);
        expect(data.loanType).toEqual('Personal');
      });
    const req = httpTestingController.expectOne({
      method: "GET",
      url: `${baseUrl}/loan-by-loanType/Personal`
    });
    req.flush(loanDetails[0]);
  });
  
  it('should return added loan details', () => {
    service.addLoanDetails(loanDetails[1]).subscribe(
      data => {
        expect(data).toBeTruthy();
        expect(data).toEqual(loanDetails[1]);
        expect(data.id).toEqual(2);
      });
    const req = httpTestingController.expectOne({
      method: "POST",
      url: `${baseUrl}/add-loan`
    });
    expect(req.request.body).toEqual(loanDetails[1]);
    req.flush(loanDetails[1]);
  });
  
  it('should return updated loan Details', () => {
    service.updateLoanDetails(2, loanDetails[1]).subscribe(
      data => {
        expect(data).toBeTruthy();
        expect(data).toEqual(loanDetails[1]);
        expect(data.id).toEqual(2);
      });
    const req = httpTestingController.expectOne({
      method: "PUT",
      url: `${baseUrl}/update-loan/2`
    });
    expect(req.request.body).toEqual(loanDetails[1]);
    req.flush(loanDetails[1]);
  });
  
  it('should send delete request', () => {
    service.deleteLoanDetails(2).subscribe(
      data => {
        expect(data).toBeTruthy();
      });
    const req = httpTestingController.expectOne({
      method: "DELETE",
      url: `${baseUrl}/delete-loan/2`
    });
    req.flush({});
  });
});
