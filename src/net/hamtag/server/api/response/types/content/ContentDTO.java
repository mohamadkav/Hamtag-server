package net.hamtag.server.api.response.types.content;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ContentDTO {
	@JsonProperty
	private Integer id;
	
	@JsonProperty
	private String type;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
}
