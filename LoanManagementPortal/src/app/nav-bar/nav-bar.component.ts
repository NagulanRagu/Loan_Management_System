import { Component, OnInit } from '@angular/core';
import { LoginServiceService } from '../service/login-service.service';

@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.css']
})
export class NavBarComponent implements OnInit {

  constructor(public loginService: LoginServiceService) { }

  public logStatus!: boolean;

  ngOnInit(): void {
    if(this.loginService.getLoginStatus()) {
      console.log(this.loginService.getLoginStatus());
      this.logStatus = this.loginService.getLoginStatus();
    }
  }

}
