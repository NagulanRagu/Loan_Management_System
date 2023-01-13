import { Component, OnInit } from '@angular/core';
import { BorrowerDetails } from '../model/borrower-details';
import { BorrowerDetailsService } from '../service/borrower-details.service';

@Component({
  selector: 'app-list-of-users',
  templateUrl: './list-of-users.component.html',
  styleUrls: ['./list-of-users.component.css']
})
export class ListOfUsersComponent implements OnInit {

  constructor(private borrowerDetailsService: BorrowerDetailsService) { }

  borrowerDetails!: BorrowerDetails[];

  ngOnInit(): void {
    this.getAllUsers();
  }

  getAllUsers() {
    this.borrowerDetailsService.getAllUsers().subscribe(
      data => {
        console.log(data);
        this.borrowerDetails = data;
      },
      error => {
        console.log(error);
      }
    )
  }
}
