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