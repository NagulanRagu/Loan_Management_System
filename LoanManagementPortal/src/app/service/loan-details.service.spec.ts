import { TestBed } from '@angular/core/testing';

import { LoanDetailsService } from './loan-details.service';

describe('LoanDetailsService', () => {
  let service: LoanDetailsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(LoanDetailsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
