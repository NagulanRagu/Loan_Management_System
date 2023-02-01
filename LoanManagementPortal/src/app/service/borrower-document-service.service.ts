import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map } from 'rxjs';
import { ResponseFile } from '../model/response-file';

@Injectable({
  providedIn: 'root'
})
export class BorrowerDocumentServiceService {

  constructor(private http: HttpClient) { }

  baseUrl: string = "http://localhost:8084"

  uploadFile(file: File, borrowerName: string, fileDetail: string) {

    const formData = new FormData();
    formData.append("file", file);
    return this.http.post<ResponseFile>(`${this.baseUrl}/${borrowerName}/uploadFile/${fileDetail}`, formData);
  }

  downloadFileByBorrowerName(borrowerName: string) {

    return this.http.get<ResponseFile[]>(`${this.baseUrl}/${borrowerName}/downloadFile`);
  }
}
