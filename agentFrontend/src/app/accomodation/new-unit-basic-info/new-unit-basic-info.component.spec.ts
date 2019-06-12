import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NewUnitBasicInfoComponent } from './new-unit-basic-info.component';

describe('NewUnitBasicInfoComponent', () => {
  let component: NewUnitBasicInfoComponent;
  let fixture: ComponentFixture<NewUnitBasicInfoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NewUnitBasicInfoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NewUnitBasicInfoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
