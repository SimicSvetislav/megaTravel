import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewAllMessagesTableComponent } from './view-all-messages-table.component';

describe('ViewAllMessagesTableComponent', () => {
  let component: ViewAllMessagesTableComponent;
  let fixture: ComponentFixture<ViewAllMessagesTableComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ViewAllMessagesTableComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewAllMessagesTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
