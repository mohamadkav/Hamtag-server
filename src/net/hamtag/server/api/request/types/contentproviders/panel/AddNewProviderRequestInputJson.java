package net.hamtag.server.api.request.types.contentproviders.panel;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AddNewProviderRequestInputJson {
	@JsonProperty("categories")
	List<String>categories;
	@JsonProperty("providername")
	String providerName;
	public List<String> getCategories() {
		return categories;
	}
	public void setCategories(List<String> categories) {
		this.categories = categories;
	}
	public String getProviderName() {
		return providerName;
	}
	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}
	
}
