package com.github.events.giteventsapi.service;

import java.util.List;

import org.springframework.web.client.RestClientException;

import com.github.events.giteventsapi.CustomException;
import com.github.events.giteventsapi.dto.Event;

public interface EventService {

	public List<Event> getEvents(String owner, String repo, String eventType)  throws RestClientException, CustomException;

}
