import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { TestBed } from '@angular/core/testing';

import { BorrowerDocumentServiceService } from './borrower-document-service.service';

describe('BorrowerDocumentServiceService', () => {
  let service: BorrowerDocumentServiceService;
  let httpTestingController: HttpTestingController;
  let baseUrl: string = "http://localhost:8084";

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule]
    });
    service = TestBed.inject(BorrowerDocumentServiceService);
    httpTestingController = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpTestingController.verify();
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
