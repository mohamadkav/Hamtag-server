package net.hamtag.server.api.response.types.news;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.hamtag.server.api.response.types.content.ContentDTO;

public class NewsDTO {
	@JsonProperty
	private Long id;
	
	@JsonProperty
	private String text;
	
	@JsonProperty
	private String title;
	
	@JsonProperty
	private String date;
	
	@JsonProperty
	private List<ContentDTO> contentInfos;
	
	@JsonProperty
	private Long providerId;
	
	@JsonProperty
	private String providerName;
	
	@JsonProperty
	private List<String> categories;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public List<ContentDTO> getContentInfos() {
		return contentInfos;
	}
	
	public List<String> getCategories() {
		return categories;
	}

	public void setCategories(List<String> categories) {
		this.categories = categories;
	}

	public void setContentInfos(List<ContentDTO> contentInfos) {
		this.contentInfos = contentInfos;
	}

	public Long getProviderId() {
		return providerId;
	}

	public void setProviderId(Long providerId) {
		this.providerId = providerId;
	}

	public String getProviderName() {
		return providerName;
	}

	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}
	
}
