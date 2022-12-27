import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { count } from 'rxjs';

@Component({
  selector: 'app-registration-successfull',
  templateUrl: './registration-successfull.component.html',
  styleUrls: ['./registration-successfull.component.css']
})
export class RegistrationSuccessfullComponent implements OnInit {

  constructor(private router: Router) { }

  ngOnInit(): void {
    // let count = 0;
    // for(let i=0; i<=4; i++) {
    //   count = count+1
    // }
    // if(count>=5) {
    //   this.router.navigate(['home']);
    // }
  }

}
