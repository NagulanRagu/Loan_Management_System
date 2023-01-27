import { Directive } from '@angular/core';
import { AbstractControl, AsyncValidator, NG_ASYNC_VALIDATORS, ValidationErrors } from '@angular/forms';
import { map, Observable } from 'rxjs';
import { BorrowerDetailsService } from '../service/borrower-details.service';

@Directive({
  selector: '[uniqueEmailValidation]',
  providers: [{ provide: NG_ASYNC_VALIDATORS, useExisting: UniqueEmailValidationDirective, multi: true }]
})
export class UniqueEmailValidationDirective implements AsyncValidator {

  constructor(private borrowerDetailsService: BorrowerDetailsService) { }

  validate(control: AbstractControl): Promise<ValidationErrors | null> | Observable<ValidationErrors | null> {
    return this.borrowerDetailsService.checkEmailId(control.value).pipe(
      map(users => {
        return users ? { 'uniqueEmailValidation': true } : null;
      })
    )
  }
}
