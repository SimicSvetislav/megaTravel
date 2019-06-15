import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AnswerMessageComponent } from './answer-message.component';

describe('AnswerMessageComponent', () => {
  let component: AnswerMessageComponent;
  let fixture: ComponentFixture<AnswerMessageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AnswerMessageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AnswerMessageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
