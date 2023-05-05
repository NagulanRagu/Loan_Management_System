import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RegisterLoanDetailsComponent } from './register-loan-details.component';

describe('RegisterLoanDetailsComponent', () => {
  let component: RegisterLoanDetailsComponent;
  let fixture: ComponentFixture<RegisterLoanDetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RegisterLoanDetailsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RegisterLoanDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
