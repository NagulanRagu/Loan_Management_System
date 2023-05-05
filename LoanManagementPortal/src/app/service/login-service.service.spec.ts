import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { TestBed } from '@angular/core/testing';
import { Address } from '../model/address';
import { BorrowerDetails } from '../model/borrower-details';
import { HttpCall } from '../model/http-call';
import { LoginCredentails } from '../model/login-credentails';
import { PersonalInformation } from '../model/personal-information';

import { LoginServiceService } from './login-service.service';

describe('LoginServiceService', () => {
  let service: LoginServiceService;
  let httpTestingController: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule]
    });
    service = TestBed.inject(LoginServiceService);
    httpTestingController = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpTestingController.verify();
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should able to sign up the new user', () => {
    const User: BorrowerDetails = {
      id: 2,
      personalInformation: new PersonalInformation(),
      uname: "Nagulan R U",
      password: "1234",
      phoneno: "8870323658",
      emailId: "runagulan88@gmail.com",
      borrowerAddress: new Address,
      enabled: false
    }
    service.signup(User).subscribe(
      data => {
        expect(data).toBeTruthy();
        expect(data).toEqual(User);
      });
    const req = httpTestingController.expectOne({
      method: "POST",
      url: 'http://localhost:8080/signup'
    });
    expect(req.request.body).toEqual(User);
    req.flush(User);
  });

  it('should able to login', () => {
    const loginCredentails: LoginCredentails = {
      uname: "Nagulan R U",
      password: "1234",
      role: []
    };
    const httpCall: HttpCall = {
      jwtToken: "Token",
      roles: ["ROLE_USER"]
    };
    service.login(loginCredentails).subscribe(
      data => {
        expect(data).toBeTruthy();
        expect(data).toEqual(httpCall);
      });
    const req = httpTestingController.expectOne({
      method: "POST",
      url: 'http://localhost:8083/authenticate'
    });
    expect(req.request.body).toEqual(loginCredentails);
    req.flush(httpCall);
    expect(sessionStorage.getItem('Authenticated User')).toEqual('Nagulan R U');
    expect(sessionStorage.getItem('Token')).toEqual('Token');
    expect(sessionStorage.getItem('Roles')).toEqual('["ROLE_USER"]');
  });

  it('should return user logged in or not', () => {
    sessionStorage.removeItem('Authenticated User');
    sessionStorage.setItem('Authenticated User', 'Nagulan R U');
    expect(service.isUserLoggedIn()).toEqual(true);    
  });

  it('should return user name', () => {
    sessionStorage.removeItem('Authenticated User');
    sessionStorage.setItem('Authenticated User', 'Nagulan R U');
    expect(service.getUserName()).toEqual('Nagulan R U');    
  });

  it('should return roles', () => {
    sessionStorage.removeItem('Roles');
    sessionStorage.setItem('Roles', '["ROLE_USER"]');
    expect(service.getRoles()).toEqual('["ROLE_USER"]');    
  });

  it('should return token', () => {
    sessionStorage.removeItem('Token');
    sessionStorage.setItem('Token', 'Token');
    expect(service.getToken()).toEqual('Token');    
  });

  it('should log out', () => {
    service.logout();
    expect(sessionStorage.getItem('Authenticated User')).toEqual(null);
    expect(sessionStorage.getItem('Token')).toEqual(null);
    expect(sessionStorage.getItem('Roles')).toEqual(null);   
  });
});

