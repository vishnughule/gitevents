import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpParams, HttpHeaders, HttpResponse} from '@angular/common/http';
import { Event } from '../model/event';


@Injectable({
  providedIn: 'root'
})
export class SearchEventService {
  constructor( private http: HttpClient) {
  }

    searchEvents(owner: string, repo: string, eventType: string): Observable<HttpResponse<Event[]>> {
    const baseurl = 'events/' + owner + '/' + repo + '/' + eventType;
    console.log(baseurl)
    return this.http.get<Event[]>(
      baseurl, { observe: 'response' });
  }


}
