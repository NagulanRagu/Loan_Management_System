import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { TestBed } from '@angular/core/testing';
import { loanRegistrations } from '../test-variables/loan-registration';

import { LoanRegistrationService } from './loan-registration.service';

describe('LoanRegistrationService', () => {
  let service: LoanRegistrationService;
  let httpTestingController: HttpTestingController;
  let baseUrl: String = 'http://localhost:8100';

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule]
    });
    service = TestBed.inject(LoanRegistrationService);
    httpTestingController = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpTestingController.verify();
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should be send the loan registration to backend', () => {
    service.sendLoanRegistration(loanRegistrations[0]).subscribe(
      data => {
        expect(data).toBeTruthy();
        expect(data).toEqual(loanRegistrations[0]);
        expect(data.id).toEqual(1);
      });
    const req = httpTestingController.expectOne({
      method: "POST",
      url: `${baseUrl}/apply`
    });
    expect(req.request.body).toEqual(loanRegistrations[0]);
    req.flush(loanRegistrations[0]);
  });

  it('should get all loan registration from backend', () => {
    service.getAll().subscribe(
      data => {
        expect(data).toBeTruthy();
        expect(data).toEqual(loanRegistrations);
        expect(data.length).toEqual(3);
      });
    const req = httpTestingController.expectOne({
      method: "GET",
      url: `${baseUrl}/all-registration`
    });
    req.flush(loanRegistrations);
  });

  it('should get loan registration by id from backend', () => {
    service.getById(2).subscribe(
      data => {
        expect(data).toBeTruthy();
        expect(data).toEqual(loanRegistrations[1]);
        expect(data.id).toEqual(2);
      });
    const req = httpTestingController.expectOne({
      method: "GET",
      url: `${baseUrl}/registration-by-id/2`
    });
    req.flush(loanRegistrations[1]);
  });

  it('should get loan registration by borrower name from backend', () => {
    service.getByBorrowerName("Nagulan R U").subscribe(
      data => {
        expect(data).toBeTruthy();
        expect(data).toEqual(loanRegistrations);
        expect(data.length).toEqual(3);
      });
    const req = httpTestingController.expectOne({
      method: "GET",
      url: `${baseUrl}/registration-by-BorrowerName/Nagulan R U`
    });
    req.flush(loanRegistrations);
  });

  it('should be send the updated loan registration to backend', () => {
    loanRegistrations[2].providedLoanAmt = "10,00,000";
    loanRegistrations[2].emiAmt = "20,000";
    loanRegistrations[2].status = "Accepted";
    service.update(loanRegistrations[2]).subscribe(
      data => {
        expect(data).toBeTruthy();
        expect(data).toEqual(loanRegistrations[2]);
        expect(data.status).toEqual("Accepted");
      });
    const req = httpTestingController.expectOne({
      method: "PUT",
      url: `${baseUrl}/update-registration`
    });
    expect(req.request.body).toEqual(loanRegistrations[2]);
    req.flush(loanRegistrations[2]);
  });
});
