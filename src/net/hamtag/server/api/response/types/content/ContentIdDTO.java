package net.hamtag.server.api.response.types.content;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ContentIdDTO {
	@JsonProperty
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
