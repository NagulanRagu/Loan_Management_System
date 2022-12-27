import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginServiceService } from '../service/login-service.service';

@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.css']
})
export class NavBarComponent implements OnInit {

  constructor(public loginService: LoginServiceService, private router: Router) { }

  public logStatus!: string;
  public routerStatus!: string;

  ngOnInit(): void {
  }

  logout(): boolean {
    if(this.loginService.isUserLoggedIn()) {
      this.loginService.logout();
      this.router.navigate(['login']);
    }
    return true;
  }
}
