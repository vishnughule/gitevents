package com.github.events.giteventsapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Actor {

	@JsonProperty(value = "id")
	private Long id;

	@JsonProperty(value = "login")
	private String login;

	@JsonProperty(value = "display_login")
	private String displayLogin;

	@JsonProperty(value = "url")
	private String url;

	@JsonProperty(value = "avatar_url")
	private String avatarUrl;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getDisplayLogin() {
		return displayLogin;
	}

	public void setDisplayLogin(String displayLogin) {
		this.displayLogin = displayLogin;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getAvatarUrl() {
		return avatarUrl;
	}

	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}
	
	

}
