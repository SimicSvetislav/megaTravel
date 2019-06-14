import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewObjectImagesComponent } from './view-object-images.component';

describe('ViewObjectImagesComponent', () => {
  let component: ViewObjectImagesComponent;
  let fixture: ComponentFixture<ViewObjectImagesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ViewObjectImagesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewObjectImagesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
