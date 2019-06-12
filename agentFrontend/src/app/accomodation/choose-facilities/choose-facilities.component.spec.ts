import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ChooseFacilitiesComponent } from './choose-facilities.component';

describe('ChooseFacilitiesComponent', () => {
  let component: ChooseFacilitiesComponent;
  let fixture: ComponentFixture<ChooseFacilitiesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ChooseFacilitiesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ChooseFacilitiesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
