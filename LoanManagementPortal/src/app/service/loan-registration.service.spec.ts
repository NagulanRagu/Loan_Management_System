import { TestBed } from '@angular/core/testing';

import { LoanRegistrationService } from './loan-registration.service';

describe('LoanRegistrationService', () => {
  let service: LoanRegistrationService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(LoanRegistrationService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
