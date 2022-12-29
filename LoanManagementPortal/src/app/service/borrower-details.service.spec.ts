import { TestBed } from '@angular/core/testing';

import { BorrowerDetailsService } from './borrower-details.service';

describe('BorrowerDetailsService', () => {
  let service: BorrowerDetailsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(BorrowerDetailsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
