package net.hamtag.server.api.response.types.device;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TokenDTO {
	@JsonProperty
	private String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
}
