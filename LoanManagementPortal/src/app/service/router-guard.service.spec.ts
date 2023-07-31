import { TestBed } from '@angular/core/testing';
import { Router } from '@angular/router';
import { AppModule } from '../app.module';
import { LoginServiceService } from './login-service.service';

import { RouterGuardService } from './router-guard.service';

describe('RouterGuardService', () => {
  let service: RouterGuardService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      // imports: [AppModule],
      providers: [
        {provide: Router, useValue: {navigate: () => ['login']}},
        {provide: LoginServiceService, useValue: {isUserLoggedIn: () => true}}
      ]
    });
    service = TestBed.inject(RouterGuardService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should return user logged in or not', () => {
  });
});
