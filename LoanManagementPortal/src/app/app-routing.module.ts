import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { LoanDetailsComponent } from './loan-details/loan-details.component';
import { LoanRegistrationComponent } from './loan-registration/loan-registration.component';
import { LoginComponent } from './login/login.component';
import { SignupComponent } from './signup/signup.component';

const routes: Routes = [
  {path : "", component : LoginComponent},
  {path : "signup", component : SignupComponent},
  {path : "login", component : LoginComponent},
  {path : "home", component : HomeComponent},
  {path : "loan", component : LoanDetailsComponent},
  {path : "loan-registration", component : LoanRegistrationComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
