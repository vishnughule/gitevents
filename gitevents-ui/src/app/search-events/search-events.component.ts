import { Component, OnInit } from '@angular/core';
import { SearchEventService } from './search-event.service';
import { Event } from '../model/event';
import { FormControl, Validators } from '@angular/forms';

@Component({
  selector: 'app-search-events',
  templateUrl: './search-events.component.html',
  styleUrls: ['./search-events.component.css']
})
export class SearchEventsComponent {

  eventsData: Event[];
  displayedColumns: string[] = ['ActorName', 'EventType', 'EventDate', 'CommitReference' , 'CommitMessage'];
  owner: string;
  repo: string;
  eventType: string;

  ownerFormControl = new FormControl('', [
    Validators.required
  ]);

  repoFormControl = new FormControl('', [
    Validators.required
  ]);

  eventTypeFormControl = new FormControl('', [
    Validators.required
  ]);

  constructor (private searchEventService: SearchEventService) {
  }
  searchEvents() {

  }
}


