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

  uname!: any;
  roles!: any;
  adminRole!: boolean;

  ngOnInit(): void {
    this.router.events.subscribe(
      data => {
        this.uname = this.loginService.getUserName();
        if(this.uname!=null){
          this.isUserAdmin();
        }
      });
  }

  logout(): boolean {
    if (this.loginService.isUserLoggedIn()) {
      this.loginService.logout();
      this.router.navigate(['login']);
    }
    return true;
  }

  isUserAdmin() {
    if (this.loginService.isUserLoggedIn()) {
      this.roles = this.loginService.getRoles();
    }
    if (this.roles.includes("ROLE_ADMIN")) {
      this.adminRole = true;
    } else {
      this.adminRole = false;
    }
  }
}