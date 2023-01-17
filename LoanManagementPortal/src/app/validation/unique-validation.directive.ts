import { Directive, forwardRef } from '@angular/core';
import { AbstractControl, FormControl, NG_ASYNC_VALIDATORS, NG_VALIDATORS, ValidationErrors, Validator } from '@angular/forms';
import { Resolve } from '@angular/router';
import { Observable } from 'rxjs';
import { CustomValidationService } from '../service/custom-validation.service';

@Directive({
  selector: '[uniqueValidation]',
  providers: [{ provide: NG_ASYNC_VALIDATORS, useExisting: forwardRef(() => UniqueValidationDirective), multi: true }]
})
export class UniqueValidationDirective implements Validator {

  constructor(private customValidationService: CustomValidationService) { }

  validate(control: AbstractControl): Promise<{ [Key: string]: any }> | Observable<{ [Key: string]: any }> {
    return this.customValidationService.userNameValidator(control);
  }
}
