import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ChoosePricelistComponent } from './choose-pricelist.component';

describe('ChoosePricelistComponent', () => {
  let component: ChoosePricelistComponent;
  let fixture: ComponentFixture<ChoosePricelistComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ChoosePricelistComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ChoosePricelistComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
