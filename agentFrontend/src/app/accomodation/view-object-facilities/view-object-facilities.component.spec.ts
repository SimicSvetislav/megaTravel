import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewObjectFacilitiesComponent } from './view-object-facilities.component';

describe('ViewObjectFacilitiesComponent', () => {
  let component: ViewObjectFacilitiesComponent;
  let fixture: ComponentFixture<ViewObjectFacilitiesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ViewObjectFacilitiesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewObjectFacilitiesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
