package com.github.events.giteventsapi.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.events.giteventsapi.CustomException;
import com.github.events.giteventsapi.dto.ApplicationConstant;
import com.github.events.giteventsapi.dto.Event;

@Service
public class EventServiceImpl implements EventService {

	@Autowired
	private RestTemplate restTemplate;

	@Override
	public List<Event> getEvents(String owner, String repo, String eventType) throws RestClientException, CustomException {
		List<Event> events = null;
		List<Event> filteredList = null;
		int totalPages = 0;

		System.out.println("Calling service for first time");
		ResponseEntity<List<Event>> response = getEventsFromAPi(owner, repo, 1);

		if (response != null) {
			events = response.getBody();
			// get links section from header to know if more pages available
			List<String> links = (List<String>) response.getHeaders().get("Link");
			// extract the last page number from links section
			totalPages = getTotalNumberOfPages(links);
			System.out.println(totalPages);
		}

		int pageNo = 2;
		while (totalPages > 1) {
			System.out.println("Calling service for next pages");
			response = getEventsFromAPi(owner, repo, pageNo);
			if (response != null) {
				events.addAll(response.getBody());
			}
			pageNo++;
			totalPages--;
		}

		System.out.println(events.size());
		filteredList = events.stream().filter(event -> event.getType().equals(eventType)).collect(Collectors.toList());

		return filteredList;

	}

	private int getTotalNumberOfPages(List<String> links) {

		int totalPages = 1;
		if (links != null && !links.isEmpty()) {
			for (String link : links) {
				if (link.contains("rel=\"last\"")) {
					totalPages = Integer.parseInt(String.valueOf(link.charAt(link.indexOf("rel=\"last\"") - 4)));
				}
			}
		}
		return totalPages;
	}

	private ResponseEntity<List<Event>> getEventsFromAPi(String owner, String repo, Integer pageNo) throws RestClientException, CustomException {

		Map<String, String> uriParams = new HashMap<String, String>();
		uriParams.put("owner", owner);
		uriParams.put("repo", repo);
		uriParams.put("page", pageNo.toString());

		UriComponents builder = UriComponentsBuilder.fromHttpUrl(ApplicationConstant.baseUrl).buildAndExpand(uriParams);

		ResponseEntity<List<Event>> response = null;

		try {
			response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, null, new ParameterizedTypeReference<List<Event>>() {
			});
		}
		catch (HttpClientErrorException hex) {
			if (hex.getRawStatusCode() == HttpStatus.NOT_FOUND.value()) {
				throw new CustomException(HttpStatus.NOT_FOUND, "Please check owner and repo, no events found for this combination");
			}
		}
		return response;

	}

}
