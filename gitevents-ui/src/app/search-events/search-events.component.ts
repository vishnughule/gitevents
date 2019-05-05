import { Component, OnInit } from '@angular/core';
import { SearchEventService } from './search-event.service';
import { Event } from '../model/event';
import { FormControl, Validators, FormGroup, FormBuilder } from '@angular/forms';
import { fromEventPattern } from '../../../node_modules/rxjs';

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
  searchForm: FormGroup;
  submitted = false;

  constructor (private searchEventService: SearchEventService, private formBuilder: FormBuilder) {
    this.createForm();
  }

  createForm(): void {
      this.searchForm = this.formBuilder.group({
        'ownerFormControl': new FormControl(this.owner, [
          Validators.required
        ]),

        'repoFormControl': new FormControl(this.repo, [
          Validators.required
        ]),

        'eventTypeFormControl': new FormControl(this.eventType, [
          Validators.required
        ])
      }
      );
  }

  get f() { return this.searchForm.controls; }

  searchEvents() {
    this.submitted = true;
    this.searchEventService.searchEvents(this.searchForm.get('ownerFormControl').value,
      this.searchForm.get('repoFormControl').value, this.searchForm.get('eventTypeFormControl').value).subscribe(
      resp => {
        this.eventsData = resp.body;
      }
    );
  }
}


