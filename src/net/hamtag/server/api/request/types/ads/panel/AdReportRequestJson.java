package net.hamtag.server.api.request.types.ads.panel;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AdReportRequestJson {
	@JsonProperty("categories")
	private List<String>categories;
	@JsonProperty("min-price")
	private Integer minPrice;
	@JsonProperty("max-price")
	private Integer maxPrice;
	public List<String> getCategories() {
		return categories;
	}
	public void setCategories(List<String> categories) {
		this.categories = categories;
	}
	public Integer getMinPrice() {
		return minPrice;
	}
	public void setMinPrice(Integer minPrice) {
		this.minPrice = minPrice;
	}
	public Integer getMaxPrice() {
		return maxPrice;
	}
	public void setMaxPrice(Integer maxPrice) {
		this.maxPrice = maxPrice;
	}
	
}
