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
  }

}
