import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewUnitBookingComponent } from './view-unit-booking.component';

describe('ViewUnitBookingComponent', () => {
  let component: ViewUnitBookingComponent;
  let fixture: ComponentFixture<ViewUnitBookingComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ViewUnitBookingComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewUnitBookingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
