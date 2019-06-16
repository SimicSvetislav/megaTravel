import { TestBed } from '@angular/core/testing';

import { AgentsServiceService } from './agents-service.service';

describe('AgentsServiceService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: AgentsServiceService = TestBed.get(AgentsServiceService);
    expect(service).toBeTruthy();
  });
});
