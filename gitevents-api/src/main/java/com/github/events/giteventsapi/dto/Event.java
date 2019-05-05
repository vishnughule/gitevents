package com.github.events.giteventsapi.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Event {

	@JsonProperty(value = "id")
	private Long id;

	@JsonProperty(value = "type")
	private String type;

	@JsonProperty(value = "public")
	private Boolean isPublic;

	@JsonProperty(value = "created_at")
	private Date createdAt;

	private Actor actor;
	
	public Event() {
		// TODO Auto-generated constructor stub
	}

	public Event(String type, Date createdAt, Actor actor) {
		this.type= type;
		this.createdAt=createdAt;
		this.actor=actor;
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Boolean getIsPublic() {
		return isPublic;
	}

	public void setIsPublic(Boolean isPublic) {
		this.isPublic = isPublic;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Actor getActor() {
		return actor;
	}

	public void setActor(Actor actor) {
		this.actor = actor;
	}

}
