package com.github.events.giteventsapi;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.github.events.giteventsapi.dto.Actor;
import com.github.events.giteventsapi.dto.Event;
import com.github.events.giteventsapi.service.EventService;
import com.github.events.giteventsapi.service.EventServiceImpl;

@RunWith(SpringRunner.class)
public class GitEventsServiceTests {

	@Mock
	private RestTemplate restTemplate;

	@InjectMocks
	private EventService empService = new EventServiceImpl();

	/**
	 * Check if empty response is coming back in response
	 * 
	 * @throws Exception
	 */
	@Test
	public void eventsServiceShouldReturnEmpty() throws Exception {

		ResponseEntity<List<Event>> response = new ResponseEntity<List<Event>>(new ArrayList<Event>(), HttpStatus.OK);

		when(restTemplate.exchange("https://api.github.com/repos/vishnughule/gitevents/events?page=1", HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Event>>() {
				})).thenReturn(response);

		assertEquals(0, empService.getEvents("vishnughule", "gitevents", "PushEvent").size());

	}

	/**
	 * Test to check if the filter by event type is working
	 */

	@Test
	public void eventServiceToCheckFilter() throws Exception {
		Actor actor = new Actor("vishnu.ghule", "vishnughule");
		Event event = new Event("PushEvent", new Date(), actor);
		List<Event> events = new ArrayList<Event>();
		events.add(event);
		Event event2 = new Event("WatchEvent", new Date(), actor);
		events.add(event2);

		ResponseEntity<List<Event>> response = new ResponseEntity<List<Event>>(events, HttpStatus.OK);
		when(restTemplate.exchange("https://api.github.com/repos/vishnughule/gitevents/events?page=1", HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Event>>() {
				})).thenReturn(response);

		List<Event> resultEvents = empService.getEvents("vishnughule", "gitevents", "PushEvent");
		assertEquals(1, resultEvents.size());
		assertEquals("PushEvent", resultEvents.get(0).getType());

	}

	/**
	 * Test to check if callig the api for next page
	 * 
	 * @throws Exception
	 */

	@Test
	public void eventServiceToCheckifTwoPages() throws Exception {
		Actor actor = new Actor("vishnu.ghule", "vishnughule");
		Event event = new Event("PushEvent", new Date(), actor);
		List<Event> eventsPage1 = new ArrayList<Event>();
		eventsPage1.add(event);
		Event event2 = new Event("PushEvent", new Date(), actor);

		List<Event> eventsPage2 = new ArrayList<Event>();
		eventsPage2.add(event2);

		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("Link", "\"<https://api.github.com/repos/vishnughule/gitevents/events?page=2>; rel=\"last\"");

		ResponseEntity<List<Event>> responsePage1 = new ResponseEntity<List<Event>>(eventsPage1, headers, HttpStatus.OK);

		when(restTemplate.exchange("https://api.github.com/repos/vishnughule/gitevents/events?page=1", HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Event>>() {
				})).thenReturn(responsePage1);

		ResponseEntity<List<Event>> responsePage2 = new ResponseEntity<List<Event>>(eventsPage2, HttpStatus.OK);
		when(restTemplate.exchange("https://api.github.com/repos/vishnughule/gitevents/events?page=2", HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Event>>() {
				})).thenReturn(responsePage2);

		List<Event> resultEvents = empService.getEvents("vishnughule", "gitevents", "PushEvent");
		assertEquals(2, resultEvents.size());

	}

	@Test
	public void eventServiceNotFoundException() throws Exception {

		when(restTemplate.exchange("https://api.github.com/repos/vishnughule/gitevents/events?page=1", HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Event>>() {
				})).thenThrow(new HttpClientErrorException(HttpStatus.NOT_FOUND));

		try {
			empService.getEvents("vishnughule", "gitevents", "PushEvent");
		}
		catch (CustomException cex) {
			assertEquals(new CustomException(HttpStatus.NOT_FOUND, "Please check owner and repo, no events found for this combination").getErrorCode(),
					cex.getErrorCode());
		}

	}

}
