import { TestBed } from '@angular/core/testing';

import { CommentMessageService } from './comment-message.service';

describe('CommentMessageService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: CommentMessageService = TestBed.get(CommentMessageService);
    expect(service).toBeTruthy();
  });
});
