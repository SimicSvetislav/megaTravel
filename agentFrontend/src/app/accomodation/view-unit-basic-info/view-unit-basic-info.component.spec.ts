import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewUnitBasicInfoComponent } from './view-unit-basic-info.component';

describe('ViewUnitBasicInfoComponent', () => {
  let component: ViewUnitBasicInfoComponent;
  let fixture: ComponentFixture<ViewUnitBasicInfoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ViewUnitBasicInfoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewUnitBasicInfoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
