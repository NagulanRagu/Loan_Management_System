import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { SignupComponent } from './signup/signup.component';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
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
    SeeLoanRegistrationComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [
    LoanRegistration,
    LoginCredentails,
    BorrowerDetails,
    LoanDetails,
    HttpCall
    // {provide: LoanRegistration, useFactory: LoanRegistration}
    // {provide: LoginStatus, useFactory: LoginStatus},
    // {provide: LoginCredentails, useFactory: LoginCredentails}
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
