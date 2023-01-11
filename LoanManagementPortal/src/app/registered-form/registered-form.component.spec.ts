import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RegisteredFormComponent } from './registered-form.component';

describe('RegisteredFormComponent', () => {
  let component: RegisteredFormComponent;
  let fixture: ComponentFixture<RegisteredFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RegisteredFormComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RegisteredFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
