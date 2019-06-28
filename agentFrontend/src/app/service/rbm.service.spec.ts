import { TestBed } from '@angular/core/testing';

import { RbmService } from './rbm.service';

describe('RbmService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: RbmService = TestBed.get(RbmService);
    expect(service).toBeTruthy();
  });
});
