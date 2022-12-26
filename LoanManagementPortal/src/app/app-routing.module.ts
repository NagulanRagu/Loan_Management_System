import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { LoanDetailsComponent } from './loan-details/loan-details.component';
import { LoanRegistrationComponent } from './loan-registration/loan-registration.component';
import { LoginComponent } from './login/login.component';
import { RouterGuardService } from './service/router-guard.service';
import { SignupComponent } from './signup/signup.component';

const routes: Routes = [
  {path : "", component : LoginComponent},
  {path : "signup", component : SignupComponent},
  {path : "login", component : LoginComponent},
  {path : "home", component : HomeComponent, canActivate: [RouterGuardService]},
  {path : "loan", component : LoanDetailsComponent, canActivate: [RouterGuardService]},
  {path : "loan-registration", component : LoanRegistrationComponent, canActivate: [RouterGuardService]}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
