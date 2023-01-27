import { Directive } from '@angular/core';
import { AbstractControl, AsyncValidator, NG_ASYNC_VALIDATORS, ValidationErrors } from '@angular/forms';
import { map, Observable } from 'rxjs';
import { BorrowerDetailsService } from '../service/borrower-details.service'

@Directive({
  selector: '[uniqueUsernameValidation]',
  providers: [{ provide: NG_ASYNC_VALIDATORS, useExisting: UniqueUsernameValidationDirective, multi: true }]
})
export class UniqueUsernameValidationDirective implements AsyncValidator {

  constructor(private borrowerDetailsService: BorrowerDetailsService) { }

  validate(control: AbstractControl): Promise<ValidationErrors | null> | Observable<ValidationErrors | null> {
    return this.borrowerDetailsService.checkUserName(control.value).pipe(
      map(users => {
        return users ? { 'uniqueUsernameValidation': true } : null;
      })
    )
  }
}
