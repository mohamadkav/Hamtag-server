package net.hamtag.server.api.request.types.news.panel;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NewsInputRequestJson {
	@JsonProperty("categories")
	List<String>categories;
	@JsonProperty("title")
	String title;
	@JsonProperty("text")
	String text;
	@JsonProperty("provider-id")
	Long providerId;
	public List<String> getCategories() {
		return categories;
	}
	public void setCategories(List<String> categories) {
		this.categories = categories;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Long getProviderId() {
		return providerId;
	}
	public void setProviderId(Long providerId) {
		this.providerId = providerId;
	}
}
