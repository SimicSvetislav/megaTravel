import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewUnitCommentsComponent } from './view-unit-comments.component';

describe('ViewUnitCommentsComponent', () => {
  let component: ViewUnitCommentsComponent;
  let fixture: ComponentFixture<ViewUnitCommentsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ViewUnitCommentsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewUnitCommentsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
