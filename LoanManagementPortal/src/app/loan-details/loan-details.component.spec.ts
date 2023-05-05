import { DebugElement } from '@angular/core';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { By } from '@angular/platform-browser';
import { Router } from '@angular/router';
import { of } from 'rxjs';
import { AppModule } from '../app.module';
import { LoanDetailsService } from '../service/loan-details.service';
import { LoginServiceService } from '../service/login-service.service';
import { loanDetails } from '../test-variables/loan-details';

import { LoanDetailsComponent } from './loan-details.component';

describe('LoanDetailsComponent', () => {
  let component: LoanDetailsComponent;
  let fixture: ComponentFixture<LoanDetailsComponent>;
  let el: DebugElement;
  let router: Router;
  let loanDetailsService: LoanDetailsService;

  beforeEach(async () => {
    TestBed.configureTestingModule({
      imports: [AppModule],
      declarations: [ LoanDetailsComponent ],
      providers: [
        {provide: Router, useValue: {navigate: () => {}}},
        {provide: LoanDetailsService, useValue: {getLoanDetails: () => of(loanDetails), 
                                                 deleteLoanDetails: () => of(loanDetails)}},
        {provide: LoginServiceService, useValue: {isUserLoggedIn: () => true, getRoles: () => ["ROLE_USER"]}}
      ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(LoanDetailsComponent);
    component = fixture.componentInstance;
    router = TestBed.inject(Router);
    el = fixture.debugElement;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should check admin login', () => {
    expect(component.adminRole).toBe(false);
  });

  it('should call return loan Details', () => {
    expect(component.loanDetails).toBeTruthy();
    expect(component.loanDetails).toEqual(loanDetails);
    expect(component.loanDetails.length).toBe(4);
  });

  it('should get details to HTML tag', () => {
    // console.log(el.nativeElement.outerHTML);
    const tr = el.queryAll(By.css(".loanDetails"));
    expect(tr).toBeTruthy();
    expect(tr.length).toBe(4);
  });

  it('should add method navigate properly', () =>{
    const add = spyOn(router, 'navigate').and.callThrough();
    fixture.detectChanges();
    component.addLoanDetails();
    expect(add).toHaveBeenCalledTimes(1);
    expect(router.navigate).toHaveBeenCalledWith(['update-loan', -1]);
  });

  it('should update method navigate properly', () =>{
    const update = spyOn(router, 'navigate').and.callThrough();
    fixture.detectChanges();
    component.updateLoanDetails(2);
    expect(update).toHaveBeenCalledTimes(1);
    expect(router.navigate).toHaveBeenCalledWith(['update-loan', 2]);
  });

  it('should delete method remove properly', () =>{
    const deleteMethod = spyOn(component, 'refreshPage').and.callThrough();
    component.deleteLoanDetails(3);
    expect(deleteMethod).toHaveBeenCalledTimes(1);
    expect(component.loanDetails).toEqual(loanDetails);
  });
});
