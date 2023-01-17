import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginCredentails } from '../model/login-credentails';
import { LoginServiceService } from '../service/login-service.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private loginService: LoginServiceService, private router: Router,
    public loginCredentails: LoginCredentails) { }

  message: string = '';
  isError: boolean = false;

  ngOnInit(): void {
    this.loginCredentails = new LoginCredentails();
  }

  login() {

    this.loginService.login(this.loginCredentails).subscribe(
      data => {
        console.log(data);
        this.router.navigate(['home']);
      },
      error => {
        console.log(error);
        this.isError = true;
        this.message = 'Username or Password are invalid';
      })
  }
}