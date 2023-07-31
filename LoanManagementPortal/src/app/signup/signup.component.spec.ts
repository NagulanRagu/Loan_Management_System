import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Router } from '@angular/router';
import { of } from 'rxjs';
import { AppModule } from '../app.module';
import { LoginServiceService } from '../service/login-service.service';
import { borrowerDetails } from '../test-variables/borrowerDetails';

import { SignupComponent } from './signup.component';

fdescribe('SignupComponent', () => {
  let component: SignupComponent;
  let fixture: ComponentFixture<SignupComponent>;
  let router: Router;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ AppModule ],
      declarations: [ SignupComponent ],
      providers: [
        {provide: LoginServiceService, useValue: {signup: () => {of(borrowerDetails)}}}
      ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SignupComponent);
    component = fixture.componentInstance;
    router = TestBed.inject(Router);
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should able to signin', () => {
    const route = spyOn(router, 'navigate').and.callThrough();
    component.borrowerDetails = borrowerDetails[1];
    component.signup();
    expect(route).toHaveBeenCalledTimes(1);
  });
});