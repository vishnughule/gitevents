package com.github.events.giteventsapi;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
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
import org.springframework.web.client.RestTemplate;

import com.github.events.giteventsapi.dto.Event;
import com.github.events.giteventsapi.service.EventService;
import com.github.events.giteventsapi.service.EventServiceImpl;

@RunWith(SpringRunner.class)
public class GitEventsServiceTests {

	@Mock
	private RestTemplate restTemplate;

	@InjectMocks
	private EventService empService = new EventServiceImpl();

	@Test
	public void eventsServiceShouldReturnEmpty() throws Exception {
		
		ResponseEntity<List<Event>> response = new ResponseEntity<List<Event>>(new ArrayList<Event>(), HttpStatus.OK);
		
		when(restTemplate.exchange("https://api.github.com/repos/vishnughule/gitevents/events?page=1", HttpMethod.GET, null,  new ParameterizedTypeReference<List<Event>>() {
		})).thenReturn(response); 
		
		assertEquals(0,  empService.getEvents("vishnughule", "gitevents", "PushEvent").size());
		
	}

}
