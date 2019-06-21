import { TestBed } from '@angular/core/testing';

import { TokeStorageService } from './toke-storage.service';

describe('TokeStorageService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: TokeStorageService = TestBed.get(TokeStorageService);
    expect(service).toBeTruthy();
  });
});
