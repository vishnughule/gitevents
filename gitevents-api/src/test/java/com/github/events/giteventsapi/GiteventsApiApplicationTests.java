package com.github.events.giteventsapi;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.events.giteventsapi.controller.EventController;
import com.github.events.giteventsapi.dto.Actor;
import com.github.events.giteventsapi.dto.Event;
import com.github.events.giteventsapi.service.EventService;

@RunWith(SpringRunner.class)
@WebMvcTest(EventController.class)
public class GiteventsApiApplicationTests {
	
	@Autowired
    private MockMvc mockMvc;

    @MockBean
    private EventService service;

    @Test
    public void eventsServiceShouldReturnEmpty() throws Exception {
        when(service.getEvents("owner", "repo", "PushEvent")).thenReturn( new ArrayList<Event>());
        MvcResult result= this.mockMvc.perform(get("/events/owner/repo/PushEvent")).andReturn();
        assertEquals(result.getResponse().getStatus(), HttpStatus.OK.value());
        assertEquals(result.getResponse().getContentAsString(), "[]");
    }
    
    @Test
    public void eventsServiceShouldReturnOneEvent() throws Exception {
    	
    	Actor actor= new Actor("vishnu.ghule", "vishnughule");
    	Event event = new Event("PushEvent", new Date(), actor);
    	List<Event> events = new ArrayList<Event>();
    	events.add(event);
    	
    	ObjectMapper mapper = new ObjectMapper();
        when(service.getEvents("owner", "repo", "PushEvent")).thenReturn(events);
        MvcResult result= this.mockMvc.perform(get("/events/owner/repo/PushEvent")).andReturn();
        assertEquals(result.getResponse().getStatus(), HttpStatus.OK.value());
        JSONAssert.assertEquals( mapper.writeValueAsString(events) , result.getResponse()
				.getContentAsString(), false);
    }
    
    
    @Test
    public void eventsServiceShouldReturnBadRequest() throws Exception {
    	 MvcResult result= this.mockMvc.perform(get("/events/owner/repo/someType")).andReturn();
    	 System.out.println(result.getResponse().getContentAsString());
    	 assertEquals(HttpStatus.UNPROCESSABLE_ENTITY.value(),result.getResponse().getStatus());
    	 assertEquals("Event type is incorrect",result.getResponse().getContentAsString());
    	 
    	 
    }
}
