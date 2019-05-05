package com.github.events.giteventsapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Actor {

	@JsonProperty(value = "login")
	private String login;

	@JsonProperty(value = "display_login")
	private String displayLogin;

	@JsonProperty(value = "url")
	private String url;

	@JsonProperty(value = "avatar_url")
	private String avatarUrl;
	
	public Actor() {
		// TODO Auto-generated constructor stub
	}

	public Actor(String login , String displayLogin) {
		this.login= login;
		this.displayLogin = displayLogin;
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
