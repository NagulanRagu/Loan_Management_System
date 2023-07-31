import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { TestBed } from '@angular/core/testing';
import { responseFile } from '../test-variables/borrowerDocument';

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
  
  it('should return borrower documents', () => {
    service.downloadFileByBorrowerName("Nagulan R U").subscribe(
      data => {
        expect(data).toBeTruthy();
        expect(data.length).toEqual(1);
        expect(data).toBe(responseFile);
      });
    const req = httpTestingController.expectOne({
      method: "GET",
      url: `${baseUrl}/Nagulan R U/downloadFile`
    });
    req.flush(responseFile);
  });

  it('should upload the borrower document', () => {
    let file: File = new File([''], "AadhaarCard.pdf");
    const formData = new FormData();
    formData.append("file", file);
    service.uploadFile(file, "Nagulan R U", "Aadhaar Card").subscribe(
      data => {
        expect(data).toBeTruthy();
        expect(data).toEqual(responseFile[0]);
      });
    const req = httpTestingController.expectOne({
      method: "POST",
      url: `${baseUrl}/Nagulan R U/uploadFile/Aadhaar Card`
    });
    req.flush(responseFile[0]);
  });
});
