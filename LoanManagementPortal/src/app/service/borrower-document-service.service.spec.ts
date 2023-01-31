import { TestBed } from '@angular/core/testing';

import { BorrowerDocumentServiceService } from './borrower-document-service.service';

describe('BorrowerDocumentServiceService', () => {
  let service: BorrowerDocumentServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(BorrowerDocumentServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
