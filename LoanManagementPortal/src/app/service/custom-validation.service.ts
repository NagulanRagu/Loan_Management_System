import { Injectable } from '@angular/core';
import { AbstractControl } from '@angular/forms';
import { Observable } from 'rxjs';
import { BorrowerDetailsService } from './borrower-details.service';

@Injectable({
  providedIn: 'root'
})
export class CustomValidationService {

  constructor(private borrowerDetailsService: BorrowerDetailsService) { }

  userNameValidator(userControl: AbstractControl): Promise<{ [Key: string]: any }> | Observable<{ [Key: string]: any }> {
    return new Promise(resolve => {
      setTimeout(() => {
        if(this.validateUserName(userControl.value)) {
          resolve({ uniqueValidation: true});
        }else {
          resolve({ uniqueValidation: false});
        }
      }, 1000);
    });
  }

  validateUserName(uname: string): boolean {
    let isUnamePresent: boolean = false;
    this.borrowerDetailsService.checkUniqueValue(uname).subscribe(
      data => {
        console.log(data); 
        isUnamePresent = data;
      });
    return isUnamePresent;
  }
}
