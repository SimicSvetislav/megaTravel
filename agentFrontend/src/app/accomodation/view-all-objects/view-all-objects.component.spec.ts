import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewAllObjectsComponent } from './view-all-objects.component';

describe('ViewAllObjectsComponent', () => {
  let component: ViewAllObjectsComponent;
  let fixture: ComponentFixture<ViewAllObjectsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ViewAllObjectsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewAllObjectsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
