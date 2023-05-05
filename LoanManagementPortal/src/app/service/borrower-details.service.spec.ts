import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { BorrowerDetailsService } from './borrower-details.service';
import { borrowerDetails } from '../test-variables/borrowerDetails';

describe('BorrowerDetailsService', () => {
  let service: BorrowerDetailsService;
  let httpTestingController: HttpTestingController;
  let baseUrl: string = 'http://localhost:8080';


  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [ HttpClientTestingModule ]
    });
    service = TestBed.inject(BorrowerDetailsService);
    httpTestingController = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpTestingController.verify();
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should get all user details', () => {
    service.getAllUsers().subscribe(
      users => {
        expect(users).toBeTruthy();
        expect(users).toEqual(borrowerDetails)
        expect(users.length).toBe(2);
      });

    const req = httpTestingController.expectOne({
      method: "GET",
      url: `${baseUrl}/all-user` 
    });
    req.flush(borrowerDetails);
  });

  it('should get user detail by borrower name', () => {
    service.getBorrowerDetails('Nagulan R U').subscribe(
      user => {
        expect(user).toBeTruthy();
        expect(user).toEqual(borrowerDetails[2]);
        expect(user.id).toBe(2);
      });
    const req = httpTestingController.expectOne({
      method: "GET",
      url: `${baseUrl}/user-by-uname/Nagulan R U` 
    });
    req.flush(borrowerDetails[2]);
  });

  it('should get user detail by id', () => {
    service.getById(2).subscribe(
      user => {
        expect(user).toBeTruthy();
        expect(user).toEqual(borrowerDetails[2]);
        expect(user.id).toBe(2);
      });
    const req = httpTestingController.expectOne({
      method: "GET",
      url: `${baseUrl}/user-by-id/2` 
    });
    req.flush(borrowerDetails[2]);
  });

  it('should update user detail', () => {
    borrowerDetails[2].phoneno = "8012946056"
    service.updateBorrowerDetails(borrowerDetails[2]).subscribe(
      user => {
        expect(user).toBeTruthy();
        expect(user).toEqual(borrowerDetails[2]);
        expect(user.id).toBe(2);
      });
    const req = httpTestingController.expectOne({
      method: "PUT",
      url: `${baseUrl}/update` 
    });
    expect(req.request.body).toEqual(borrowerDetails[2]);
    req.flush(borrowerDetails[2]);
  });

  it('should return whether username present or not', () => {
    const uname: string = "Nagulan R U"
    service.checkUserName(uname).subscribe(
      data => {
        expect(data).toBeTruthy();
        expect(data).toBe(true);
      });
      const req = httpTestingController.expectOne({
        method: "POST",
        url: `${baseUrl}/user-by-uname` 
      });
      expect(req.request.body).toEqual(uname);
      req.flush(true);
  });

  it('should return whether email id present or not', () => {
    const emailId: string = "runagulan88@gmail.com"
    service.checkEmailId(emailId).subscribe(
      data => {
        expect(data).toBeTruthy();
        expect(data).toBe(true);
      });
      const req = httpTestingController.expectOne({
        method: "POST",
        url: `${baseUrl}/user-by-emailId`
      });
      expect(req.request.body).toEqual(emailId);
      req.flush(true);
  });
});
