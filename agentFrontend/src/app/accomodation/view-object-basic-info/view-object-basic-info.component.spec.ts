import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewObjectBasicInfoComponent } from './view-object-basic-info.component';

describe('ViewObjectBasicInfoComponent', () => {
  let component: ViewObjectBasicInfoComponent;
  let fixture: ComponentFixture<ViewObjectBasicInfoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ViewObjectBasicInfoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewObjectBasicInfoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
