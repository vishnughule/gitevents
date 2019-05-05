package com.github.events.giteventsapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;

import com.github.events.giteventsapi.CustomException;
import com.github.events.giteventsapi.dto.ApplicationConstant;
import com.github.events.giteventsapi.dto.Event;
import com.github.events.giteventsapi.service.EventService;

@RestController
public class EventController {

	@Autowired
	private EventService eventService;

	@GetMapping("/events/{owner}/{repo}/{eventType}")
	public ResponseEntity<Object> searchEvents(@PathVariable("owner") String owner, @PathVariable("repo") String repo,
			@PathVariable("eventType") String eventType) {
		HttpStatus responseStatus = HttpStatus.OK;
		List<Event> events = null;
		if(!ApplicationConstant.eventTypes.contains(eventType))
		{
			return new ResponseEntity<Object>("Event type is incorrect",HttpStatus.UNPROCESSABLE_ENTITY);
		}
		
		try {
		events=eventService.getEvents(owner, repo, eventType);
		} 
		catch (CustomException cex) {
			return new ResponseEntity<Object>(cex.getMessage(),cex.getErrorCode());
		}
		catch (RestClientException rex) {
			return new ResponseEntity<Object>("Something went wrong in invoking Git API", responseStatus);
		}
		return new ResponseEntity<Object>(events, responseStatus);
		

	}

}
