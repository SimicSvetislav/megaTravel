import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NewObjectBasicInfoComponent } from './new-object-basic-info.component';

describe('NewObjectBasicInfoComponent', () => {
  let component: NewObjectBasicInfoComponent;
  let fixture: ComponentFixture<NewObjectBasicInfoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NewObjectBasicInfoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NewObjectBasicInfoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
