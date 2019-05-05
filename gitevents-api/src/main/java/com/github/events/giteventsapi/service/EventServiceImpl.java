package com.github.events.giteventsapi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.github.events.giteventsapi.dto.Event;



@Service
public class EventServiceImpl implements EventService {

	@Override
	public List<Event> getEvents(String owner, String repo, String eventType) {
			return null;
	}
}
