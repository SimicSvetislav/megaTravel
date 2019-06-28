import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RbmPanelComponent } from './rbm-panel.component';

describe('RbmPanelComponent', () => {
  let component: RbmPanelComponent;
  let fixture: ComponentFixture<RbmPanelComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RbmPanelComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RbmPanelComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
