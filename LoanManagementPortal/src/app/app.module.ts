import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { SignupComponent } from './signup/signup.component';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { FormsModule } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { NavBarComponent } from './nav-bar/nav-bar.component';
import { LoanDetailsComponent } from './loan-details/loan-details.component';
import { LoanRegistrationComponent } from './loan-registration/loan-registration.component';
import { LoginCredentails } from './model/login-credentails';
import { LoanRegistration } from './model/loan-registration';
import { BorrowerDetails } from './model/borrower-details';
import { LoanDetails } from './model/loan-details';
import { HttpCall } from './model/http-call';
import { RegistrationSuccessfullComponent } from './submitted/registration-successfull/registration-successfull.component';
import { AddLoanDetailsComponent } from './add-loan-details/add-loan-details.component';
import { SeeLoanRegistrationComponent } from './see-loan-registration/see-loan-registration.component';
import { ProfileComponent } from './profile/profile.component';
import { TokenInterceptorService } from './service/token-interceptor.service';
import { GuarantorInfo } from './model/guarantor-info';
import { Address } from './model/address';
import { GuarantorAddress } from './model/guarantor-address';
import { RegisteredFormComponent } from './registered-form/registered-form.component';

@NgModule({
  declarations: [
    AppComponent,
    SignupComponent,
    LoginComponent,
    HomeComponent,
    NavBarComponent,
    LoanDetailsComponent,
    LoanRegistrationComponent,
    RegistrationSuccessfullComponent,
    AddLoanDetailsComponent,
    SeeLoanRegistrationComponent,
    ProfileComponent,
    RegisteredFormComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [
    {provide: HTTP_INTERCEPTORS, useClass: TokenInterceptorService, multi: true},
    LoanRegistration,
    LoginCredentails,
    BorrowerDetails,
    LoanDetails,
    HttpCall,
    GuarantorInfo,
    Address,
    GuarantorAddress
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
