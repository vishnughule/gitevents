package com.github.events.giteventsapi.service;

import java.util.List;

import com.github.events.giteventsapi.dto.Event;

public interface EventService {

	public List<Event> getEvents(String owner, String repo, String eventType);

}
