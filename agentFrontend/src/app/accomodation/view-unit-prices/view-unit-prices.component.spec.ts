import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewUnitPricesComponent } from './view-unit-prices.component';

describe('ViewUnitPricesComponent', () => {
  let component: ViewUnitPricesComponent;
  let fixture: ComponentFixture<ViewUnitPricesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ViewUnitPricesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewUnitPricesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
