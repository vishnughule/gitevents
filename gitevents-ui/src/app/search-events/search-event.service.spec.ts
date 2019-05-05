import { TestBed, inject } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import {HttpClientModule} from '@angular/common/http';
import { SearchEventService } from './search-event.service';

describe('SearchEventService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [SearchEventService]
    });
  });

  it('should be created', inject([SearchEventService], (service: SearchEventService) => {
    expect(service).toBeTruthy();
  }));
});
