package net.hamtag.server.api.response.types.device;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NewVersionDTO {
	@JsonProperty
	private double version;
	@JsonProperty
	private String text;
	@JsonProperty
	private String link;
	public double getVersion() {
		return version;
	}
	public void setVersion(double version) {
		this.version = version;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	
	
}
