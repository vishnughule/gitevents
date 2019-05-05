package com.github.events.giteventsapi.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatTypes;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Event {

	@JsonProperty(value = "type")
	private String type;

	@JsonProperty(value = "public")
	private Boolean isPublic;

	@JsonProperty(value = "created_at")
	@JsonFormat(shape= JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
	private Date createdAt;

	private Actor actor;

	@JsonProperty(value = "payload")
	private Payload payLoad;

	public Event() {
		// TODO Auto-generated constructor stub
	}

	public Event(String type, Date createdAt, Actor actor) {
		this.type = type;
		this.createdAt = createdAt;
		this.actor = actor;

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

	public Payload getPayLoad() {
		return payLoad;
	}

	public void setPayLoad(Payload payLoad) {
		this.payLoad = payLoad;
	}

}
