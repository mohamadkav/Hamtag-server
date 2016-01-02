package net.hamtag.server.api.response.types;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AdInfoDTO {
	@JsonProperty("test_string")
	private String testString;

	public String getTestString() {
		return testString;
	}

	public void setTestString(String testString) {
		this.testString = testString;
	}
	
}
