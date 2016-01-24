package net.hamtag.server.api.response.types.content;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ContentDTO {
	@JsonProperty
	private Long id;
	
	@JsonProperty
	private String type;

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
	
	
}
