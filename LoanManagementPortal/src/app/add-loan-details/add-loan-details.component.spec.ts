import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddLoanDetailsComponent } from './add-loan-details.component';

describe('AddLoanDetailsComponent', () => {
  let component: AddLoanDetailsComponent;
  let fixture: ComponentFixture<AddLoanDetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddLoanDetailsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddLoanDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
