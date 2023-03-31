import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddLoanDetailsComponent } from './add-loan-details/add-loan-details.component';
import { PersonalInfoComponent } from './apply-loan/personal-info/personal-info.component';
import { InternalServerErrorComponent } from './errors/internal-server-error/internal-server-error.component';
import { HomeComponent } from './home/home.component';
import { ListOfUsersComponent } from './list-of-users/list-of-users.component';
import { LoanDetailsComponent } from './loan-details/loan-details.component';
import { LoanRegistrationComponent } from './loan-registration/loan-registration.component';
import { LoginComponent } from './login/login.component';
import { ProfileComponent } from './profile/profile.component';
import { RegisteredFormComponent } from './registered-form/registered-form.component';
import { SeeLoanRegistrationComponent } from './see-loan-registration/see-loan-registration.component';
import { RouterGuardService } from './service/router-guard.service';
import { SignupComponent } from './signup/signup.component';
import { RegistrationSuccessfullComponent } from './submitted/registration-successfull/registration-successfull.component';

const routes: Routes = [
  { path: "", component: LoginComponent },
  { path: "signup", component: SignupComponent },
  { path: "login", component: LoginComponent },
  { path: "home", component: HomeComponent, canActivate: [RouterGuardService] },
  { path: "loan", component: LoanDetailsComponent, canActivate: [RouterGuardService] },
  { path: "update-loan/:id", component: AddLoanDetailsComponent, canActivate: [RouterGuardService] },
  { path: "loan-registration", component: LoanRegistrationComponent, canActivate: [RouterGuardService] },
  { path: "registration-success", component: RegistrationSuccessfullComponent, canActivate: [RouterGuardService] },
  { path: "all-registration", component: SeeLoanRegistrationComponent, canActivate: [RouterGuardService] },
  { path: "profile/:uname", component: ProfileComponent, canActivate: [RouterGuardService] },
  { path: "registeredForm/:id", component: RegisteredFormComponent, canActivate: [RouterGuardService] },
  { path: "allUsers", component: ListOfUsersComponent, canActivate: [RouterGuardService] },
  { path: "internal-server-error", component: InternalServerErrorComponent},
  { path: "applyloan/personalInfo", component: PersonalInfoComponent, canActivate: [RouterGuardService] }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
