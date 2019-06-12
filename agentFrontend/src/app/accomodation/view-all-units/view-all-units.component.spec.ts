import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewAllUnitsComponent } from './view-all-units.component';

describe('ViewAllUnitsComponent', () => {
  let component: ViewAllUnitsComponent;
  let fixture: ComponentFixture<ViewAllUnitsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ViewAllUnitsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewAllUnitsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
